package edu.co.software.smap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.co.software.smap.model.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {

	@Query(value = "SELECT * FROM TIPO WHERE name = ?1", nativeQuery = true)
	Tipo findByName(String name);
}
