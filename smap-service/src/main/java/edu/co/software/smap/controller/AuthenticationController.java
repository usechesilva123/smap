package edu.co.software.smap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.co.software.smap.model.AuthenticationRequest;
import edu.co.software.smap.model.AuthenticationResponse;
import edu.co.software.smap.model.Usuario;
import edu.co.software.smap.repository.UsuarioRepository;
import edu.co.software.smap.security.UserDetailService;
import edu.co.software.smap.utils.JwtUtil;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class AuthenticationController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest usuario) throws Exception{
		try {
			log.info(usuario.getUsername() + "   -   " + usuario.getPassword());
		authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword())
				);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Credenciales incorrectas");
		}
		
		final UserDetails userDetails = userDetailService.
				loadUserByUsername(usuario.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		Usuario user = userRepository.findByUser(usuario.getUsername());
		String role = (user.getRoles()).iterator().next().getName();
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt, role , user));
	}

}
