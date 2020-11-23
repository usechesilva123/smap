package edu.co.software.smap.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
@Embeddable
public class Radicado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2202021101022968811L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	Integer numero_radicado;
	@Column(nullable = false)
	Date fecha;
	@Column(nullable = false, length = 3000)
	String comentario;
	@Column(nullable = false)
	String anexo;
	
	@ManyToOne
	@JoinColumn(name="usuario_id", referencedColumnName = "id", nullable = false)
	Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="estado_id", referencedColumnName = "id", nullable = false)
	Estado estado;
	
	@ManyToOne
	@JoinColumn(name="tipo_id", referencedColumnName = "id", nullable = false)
	Tipo tipo;	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "radicado")
	@JsonManagedReference
	public Set<Respuesta> respuestas = new HashSet<Respuesta>(0);
	
	

	public Radicado() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the tipo
	 */
	public Tipo getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the numero_radicado
	 */
	public Integer getNumero_radicado() {
		return numero_radicado;
	}
	/**
	 * @param numero_radicado the numero_radicado to set
	 */
	public void setNumero_radicado(Integer numero_radicado) {
		this.numero_radicado = numero_radicado;
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
	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
	 * @return the anexo
	 */
	public String getAnexo() {
		return anexo;
	}
	/**
	 * @param anexo the anexo to set
	 */
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	@Override
	public boolean equals(Object o) {
		if (this== o) return true;
		if (o ==null|| getClass() != o.getClass()) return false;

		return true;
	}

	public int hashCode() {
		return super.hashCode();
	}   

}

