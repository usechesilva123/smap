package edu.co.software.smap.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Role;
import edu.co.software.smap.model.Usuario;
import edu.co.software.smap.repository.UsuarioRepository;
import edu.co.software.smap.repository.RoleRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Usuario saveAdmin(Usuario usuario) {
		// TODO Auto-generated method stub
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_ADMIN"));
		usuario.setRoles(roles);
		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario saveClient(Usuario usuario) {
		// TODO Auto-generated method stub
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_CLIENT"));
		usuario.setRoles(roles);
		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> fetch() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
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
