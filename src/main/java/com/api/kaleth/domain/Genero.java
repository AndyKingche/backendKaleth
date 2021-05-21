package com.api.kaleth.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Table(name="genero")
public class Genero implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id_genero")
	private Long id_genero;
	
	@Column(name="nombre",length = 20)
	private String nombre;
	public Genero() {
		
	}

	public Long getId_genero() {
		return id_genero;
	}

	public void setId_genero(Long id_genero) {
		this.id_genero = id_genero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
