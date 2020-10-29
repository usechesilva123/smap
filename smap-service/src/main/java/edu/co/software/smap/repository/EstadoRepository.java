package edu.co.software.smap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.co.software.smap.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	@Query(value = "SELECT * FROM Estado WHERE name = ?1", nativeQuery = true)
	Estado findByName(String name);
}
