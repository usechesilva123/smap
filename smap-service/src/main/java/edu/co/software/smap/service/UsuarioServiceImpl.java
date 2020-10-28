package edu.co.software.smap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Usuario;
import edu.co.software.smap.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario fetch(Integer id) {
		// TODO Auto-generated method stub
		return usuarioRepository.getOne(id);
	}

	@Override
	public void delete(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepository.delete(usuario);
	}

	@Override
	public Usuario findByUser(String user) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByUser(user);
	}
	

}
