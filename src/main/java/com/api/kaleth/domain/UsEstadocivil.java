package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * The persistent class for the us_estadocivil database table.
 * 
 */
@Entity
@Table(name="us_estadocivil")
@NamedQuery(name="UsEstadocivil.findAll", query="SELECT u FROM UsEstadocivil u")
public class UsEstadocivil implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID_ESTADOCIVIL")
	private Long idEstadocivil;

	private String nombre;



	public UsEstadocivil() {
	}

	

	public Long getIdEstadocivil() {
		return idEstadocivil;
	}



	public void setIdEstadocivil(Long idEstadocivil) {
		this.idEstadocivil = idEstadocivil;
	}



	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}