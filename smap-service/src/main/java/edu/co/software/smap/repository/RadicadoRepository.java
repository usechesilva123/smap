package edu.co.software.smap.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.co.software.smap.model.Radicado;

public interface RadicadoRepository extends JpaRepository<Radicado, Integer> {

	@Query(value = "SELECT * FROM RADICADO WHERE ID = ?1", nativeQuery = true)
	Optional<Radicado> findById(Integer id);
	
	@Query(value = "SELECT * FROM RADICADO WHERE USUARIO_ID = ?1 ORDER BY FECHA DESC", nativeQuery = true)
	List<Radicado> findByUser(Integer id);
}
