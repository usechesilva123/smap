package edu.co.software.smap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.co.software.smap.model.Usuario;
import edu.co.software.smap.model.Radicado;
import edu.co.software.smap.service.EstadoService;
import edu.co.software.smap.service.FileStorageService;
import edu.co.software.smap.service.RadicadoService;
import edu.co.software.smap.service.TipoService;
import edu.co.software.smap.service.UsuarioService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class RadicadoController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(RadicadoController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private RadicadoService radicadoService;

	@Autowired
	private TipoService tipoService;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/registrarRadicado")
	public Radicado registrarRadicado(@RequestParam("file") MultipartFile file,
			@RequestParam("comentario") String comment, @RequestParam("tipo") String tipo,
			@RequestParam("estado") String estado, @RequestParam("documento") String documento,
			@RequestParam("email") String email, @RequestParam("telefono") String telefono,
			@RequestParam("nombre_completo") String nombre_completo, @RequestParam("password") String password,
			@RequestParam("tipo_documento") String tipo_documento) throws Exception {
		
		Usuario user = usuarioService.findByUser(documento);
		if(user==null) { 
			user = new Usuario(nombre_completo, documento, telefono, email, tipo_documento, password, true);
			usuarioService.saveClient(user);
		}
		
		Radicado radicado = new Radicado();
		radicado.setComentario(comment);
		radicado.setTipo(tipoService.findByName(tipo));
		radicado.setEstado(estadoService.findByName(estado));
		radicado.setFecha(new Date(System.currentTimeMillis()));
		radicado.setUsuario(user);
		radicado = radicadoService.saveRadicado(radicado);
		radicado.setNumero_radicado((int)radicado.getId()+9900);

		String fileName = fileStorageService.storeFile(file, radicado.getNumero_radicado()+".pdf");

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(fileName)
				.toUriString();


		radicado.setAnexo(fileDownloadUri);
		
		radicado = radicadoService.saveRadicado(radicado);

		return radicado;
//		return new UploadFileResponse(fileName, fileDownloadUri,
//				file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
}