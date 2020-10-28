package edu.co.software.smap.model;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Role {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<Usuario> users;
 
    @ManyToMany
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

	public Role(String name) {
		super();
		this.name = name;
	}
	
	public Role() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Usuario> getUsers() {
		return users;
	}

	public void setUsers(Collection<Usuario> users) {
		this.users = users;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}   
}