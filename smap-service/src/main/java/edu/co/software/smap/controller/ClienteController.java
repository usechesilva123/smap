package edu.co.software.smap.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.co.software.smap.model.Radicado;
import edu.co.software.smap.service.RadicadoService;
import edu.co.software.smap.service.UsuarioService;
import edu.co.software.smap.utils.JwtUtil;

@RestController
@RequestMapping(value = "/client")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class ClienteController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private RadicadoService radicadoService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/radicado")
	public List<Radicado> radicados(HttpServletRequest request){		
		final String authorizationHeader = request.getHeader("Authorization");		
		String username = null;
		String jwt = null;		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}
		int id = usuarioService.findByUser(username).getId();
		return radicadoService.findByUser(id);
	}

}
