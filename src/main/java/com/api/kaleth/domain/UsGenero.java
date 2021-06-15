package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the us_genero database table.
 * 
 */
@Entity
@Table(name="us_genero")
@NamedQuery(name="UsGenero.findAll", query="SELECT u FROM UsGenero u")
public class UsGenero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_GENERO")
	private Long idGenero;

	private String genero;

	private String nombre;

	public UsGenero() {
	}

	

	public Long getIdGenero() {
		return idGenero;
	}



	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}



	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}