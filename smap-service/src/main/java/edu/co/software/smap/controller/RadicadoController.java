package edu.co.software.smap.controller;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import edu.co.software.smap.utils.PasswordGenerator;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class RadicadoController {

	private static final int PASS_LENGHT = 8;

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

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private VelocityEngine velocityEngine;

	public String prepareRegistrationEmailText(Usuario user) {
		VelocityContext context = new VelocityContext();
		context.put("nombre_completo", user.getNombre_completo());
		context.put("documento", user.getDocumento());
		context.put("hash", user.getPassword());
		StringWriter stringWriter = new StringWriter();
		velocityEngine.mergeTemplate("templates/registration-confirmation.vm", "UTF-8", context, stringWriter);
		String text = stringWriter.toString();
		return text;
	}

	@PostMapping("/registrarRadicado")
	public Radicado registrarRadicado(@RequestParam("file") MultipartFile file,
			@RequestParam("comentario") String comment, @RequestParam("tipo") String tipo,
			@RequestParam("estado") String estado, @RequestParam("documento") String documento,
			@RequestParam("email") String email, @RequestParam("telefono") String telefono,
			@RequestParam("nombre_completo") String nombre_completo, @RequestParam("password") String password,
			@RequestParam("tipo_documento") String tipo_documento) throws Exception {

		Usuario user = usuarioService.findByUser(documento);


		if(user==null) { 
			String pss = PasswordGenerator.generateRandomPassword(PASS_LENGHT);
			user = new Usuario(nombre_completo, documento, telefono, email, tipo_documento, pss, true);

			MimeMessage message = sender.createMimeMessage();

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(email);
			helper.setSubject("Gracias por ayudarnos a mejorar");
			// Retome
			String text = prepareRegistrationEmailText(user);

			// use the true flag to indicate the text included is HTML
			helper.setText(text, true);


			usuarioService.saveClient(user);

			sender.send(message);
		}


		Radicado radicado = new Radicado();
		radicado.setComentario(comment);
		radicado.setTipo(tipoService.findByName(tipo));
		radicado.setEstado(estadoService.findByName(estado));
		radicado.setFecha(new Date(System.currentTimeMillis()));
		radicado.setUsuario(user);
		radicado.setAnexo("");
		radicado.setNumero_radicado(0);
		//Para obtener el id de radicado válido
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