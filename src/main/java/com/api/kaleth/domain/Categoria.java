package com.api.kaleth.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="categorias")
public class Categoria implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy="native")
	@Column(name="id_categoria")
	private Long id_categoria;
	
	@Column(name="nombre", length = 50)
	private String nombre;
	
	@Column(name="descripcion",length = 200)
	private String descripcion;
	
	public Categoria() {
		
	}
	

	public Categoria(Long id_categoria, String nombre, String descripcion) {
		super();
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}



	public Long getId_categoria() {
		return id_categoria;
	}


	public void setId_categoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
	
}
