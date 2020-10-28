package edu.co.software.smap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Respuesta {

	@Id
	@Column(name = "id")
	int id;
	
	String justificacion;
	
	@ManyToOne
	@JoinColumn(name="radicado_id", referencedColumnName = "id")
	Radicado radicado;

	@JoinColumn(name="estado_id", referencedColumnName = "id")
	Estado estado;
	
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
