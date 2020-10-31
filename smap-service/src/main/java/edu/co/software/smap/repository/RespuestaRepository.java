package edu.co.software.smap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.co.software.smap.model.Respuesta;

public interface RespuestaRepository extends JpaRepository<Respuesta, Integer>{

	@Query(value = "SELECT * FROM RESPUESTA WHERE ID = ?1", nativeQuery = true)
	Optional<Respuesta> findById(Integer id);
}
