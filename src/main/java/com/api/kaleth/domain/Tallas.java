package com.api.kaleth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tallas")
public class Tallas {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="id_tallas")
	private Long id_tallas;
	
	@Column(name="nombre",length = 20)
	private String nombre;
	
	@Column(name="descripcion", columnDefinition="TEXT")
	private String descripcion;
	
	public Tallas() {
		
	}
	
	public Tallas(Long id_tallas, String nombre, String descripcion) {
		super();
		this.id_tallas = id_tallas;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	

	public Long getId_tallas() {
		return id_tallas;
	}

	public void setId_tallas(Long id_tallas) {
		this.id_tallas = id_tallas;
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

