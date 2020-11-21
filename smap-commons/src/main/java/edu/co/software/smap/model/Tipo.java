package edu.co.software.smap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tipo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6623926709036368660L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
    private String name;

	public Tipo(String name) {
		super();
		this.name = name;
	}
	
	public Tipo() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the codigo
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setName(String name) {
		this.name = name;
	}
    


}
