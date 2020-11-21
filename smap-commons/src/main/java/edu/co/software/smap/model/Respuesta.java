package edu.co.software.smap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	@Column(nullable = false)
	String justificacion;
	
	@ManyToOne
	@JoinColumn(name="radicado_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	Radicado radicado;
	
	@ManyToOne
	@JoinColumn(name="usuario_id", referencedColumnName = "id", nullable = false)
	Usuario usuario;

	@ManyToOne
	@JoinColumn(name="estado_id", referencedColumnName = "id", nullable = false)
	Estado estado;

	@Column(nullable = false)
	Date fecha;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the justificacion
	 */
	public String getJustificacion() {
		return justificacion;
	}

	/**
	 * @param justificacion the justificacion to set
	 */
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	/**
	 * @return the radicado
	 */
	public Radicado getRadicado() {
		return radicado;
	}

	/**
	 * @param radicado the radicado to set
	 */
	public void setRadicado(Radicado radicado) {
		this.radicado = radicado;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
