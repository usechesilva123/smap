package edu.co.software.smap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.co.software.smap.model.Radicado;
import edu.co.software.smap.model.Respuesta;
import edu.co.software.smap.model.Usuario;
import edu.co.software.smap.service.RadicadoService;
import edu.co.software.smap.service.RespuestaService;
import edu.co.software.smap.service.UsuarioService;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class AdministradorController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RespuestaService respuestaService;
	
	@Autowired
	private RadicadoService radicadoService;
	
	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	public Usuario save(@RequestBody Usuario usuario) {
		return usuarioService.saveAdmin(usuario);
	}
	
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public List<Usuario> find() {
		return usuarioService.fetch();
	}
	
	@RequestMapping(value = "/usuario", method = RequestMethod.DELETE)
	public void delete(@RequestBody Usuario usuario) {
		usuarioService.delete(usuario);
	}
	
	@RequestMapping(value = "/responder", method = RequestMethod.POST)
	public void responder(@RequestBody Respuesta respuesta) {
		Radicado radicado = respuesta.getRadicado();
		radicado.setEstado(respuesta.getEstado());
		radicadoService.saveRadicado(radicado);
		respuestaService.save(respuesta);
	}

	@GetMapping("/radicado")
	public List<Radicado> radicados(){
		return radicadoService.fetch();
	}

}
