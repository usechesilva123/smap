package edu.co.software.smap.service;

import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Usuario;

@Service
public interface UsuarioService {
	
	Usuario save(Usuario usuario);

	Usuario fetch(Integer id);

	Usuario findByUser(String user);
	
	void delete(Usuario usuario);

}
