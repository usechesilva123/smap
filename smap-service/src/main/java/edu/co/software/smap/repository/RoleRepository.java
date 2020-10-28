package edu.co.software.smap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.co.software.smap.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	@Query(value = "SELECT * FROM ROLE WHERE name = ?1", nativeQuery = true)
	Role findByName(String name);
}
