package edu.co.software.smap.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Usuario;

@Service
public interface UsuarioService {
	
	Usuario saveAdmin(Usuario usuario);
	
	Usuario saveClient(Usuario usuario);

	List<Usuario> fetch();

	Usuario findByUser(String user);
	
	void delete(Usuario usuario);

}
