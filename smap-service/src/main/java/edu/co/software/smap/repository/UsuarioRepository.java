package edu.co.software.smap.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.co.software.smap.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> { 
	@Query(value = "Select * FROM Usuario u WHERE u.documento = ?1", nativeQuery = true)
	Usuario findByUser(String user);
}
