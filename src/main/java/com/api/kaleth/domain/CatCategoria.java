package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * The persistent class for the cat_categoria database table.
 * 
 */
@Entity
@Table(name="cat_categoria")
@NamedQuery(name="CatCategoria.findAll", query="SELECT c FROM CatCategoria c")
public class CatCategoria implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID_CATEGORIA")
	private Long idCategoria;

	private String descripcion;

	@Column(name="NOMBRE_CATEGORIA")
	private String nombreCategoria;

	

	public CatCategoria() {
	}

	public Long getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreCategoria() {
		return this.nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}



}