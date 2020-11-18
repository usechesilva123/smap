package edu.co.software.smap.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.co.software.smap.model.Estado;
import edu.co.software.smap.model.Privilege;
import edu.co.software.smap.model.Role;
import edu.co.software.smap.model.Tipo;
import edu.co.software.smap.model.Usuario;
import edu.co.software.smap.repository.EstadoRepository;
import edu.co.software.smap.repository.PrivilegeRepository;
import edu.co.software.smap.repository.RoleRepository;
import edu.co.software.smap.repository.TipoRepository;
import edu.co.software.smap.repository.UsuarioRepository;
import edu.co.software.smap.security.BCryptPasswordEncoder;

@Component
public class InitialDataLoader implements
ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private TipoRepository tipoRepository;

	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		Privilege readPrivilege
		= createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege writePrivilege
		= createPrivilegeIfNotFound("WRITE_PRIVILEGE");

		List<Privilege> adminPrivileges = Arrays.asList(
				readPrivilege, writePrivilege);        
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_CLIENT", Arrays.asList(readPrivilege));

		createEstadoIfNotFound("PENDIENTE");
		createEstadoIfNotFound("RESUELTO");
		createEstadoIfNotFound("REVISION");
		createEstadoIfNotFound("DESCARTADO");

		createTipoIfNotFound("PETICION");
		createTipoIfNotFound("QUEJA");
		createTipoIfNotFound("RECLAMO");
		createTipoIfNotFound("SUGERENCIA");

		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		Role clientRole = roleRepository.findByName("ROLE_CLIENT");
		if(userRepository.findByUser("1018496318")==null) {
			Usuario user = new Usuario("DIEGO USECHE", "1018496318", "3197521741", "usechesilva@hotmail.es"
					, "CC", passwordEncoder.encode("admin"), true);
			ArrayList<Role> roles = new ArrayList<Role>();
			user.setId(1);
			roles.add(adminRole);
			roles.add(clientRole);
			user.setRoles(roles);
			userRepository.save(user);
		}
		alreadySetup = true;
	}

	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private Tipo createTipoIfNotFound(String name) {

		Tipo tipo = tipoRepository.findByName(name);
		if (tipo == null) {
			tipo = new Tipo(name);
			tipoRepository.save(tipo);
		}
		return tipo;
	}

	@Transactional
	private Estado createEstadoIfNotFound(String name) {

		Estado estado = estadoRepository.findByName(name);
		if (estado == null) {
			estado = new Estado(name);
			estadoRepository.save(estado);
		}
		return estado;
	}

	@Transactional
	private Role createRoleIfNotFound(
			String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}
}
