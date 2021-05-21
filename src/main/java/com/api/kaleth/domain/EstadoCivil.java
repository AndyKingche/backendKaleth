package com.api.kaleth.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="estadocivil")
public class EstadoCivil implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id_estadocivil")
	private Long id_estadocivil;
	
	@Column(name="nombre",length =20 )
	private String nombre;
	
	public EstadoCivil() {
		
	}

	public Long getId_estadocivil() {
		return id_estadocivil;
	}

	public void setId_estadocivil(Long id_estadocivil) {
		this.id_estadocivil = id_estadocivil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
