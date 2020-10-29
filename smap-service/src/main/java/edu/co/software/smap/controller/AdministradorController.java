package edu.co.software.smap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.co.software.smap.model.Usuario;
import edu.co.software.smap.service.UsuarioService;

@RestController
@RequestMapping(value = "/admin")
public class AdministradorController {
	
	@Autowired
	UsuarioService usuarioService;
	
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

}
