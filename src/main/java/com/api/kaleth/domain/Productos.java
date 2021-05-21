package com.api.kaleth.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="productos")
public class Productos implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy="native")
	@Column(name="id_productos")
	private Long id_productos;
	
	@ManyToOne
	@JoinColumn(name="categorias_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="tallas_id")
	private Tallas tallas;
	
	@ManyToOne
	@JoinColumn(name="disenos_id")
	private Disenos disenos;
	
	public Productos() {
		
	}

	public Productos(Long id_productos, Categoria categoria, Tallas tallas, Disenos disenos) {
		super();
		this.id_productos = id_productos;
		this.categoria = categoria;
		this.tallas = tallas;
		this.disenos = disenos;
	}

	public Long getId_productos() {
		return id_productos;
	}

	public void setId_productos(Long id_productos) {
		this.id_productos = id_productos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Tallas getTallas() {
		return tallas;
	}

	public void setTallas(Tallas tallas) {
		this.tallas = tallas;
	}

	public Disenos getDisenos() {
		return disenos;
	}

	public void setDisenos(Disenos disenos) {
		this.disenos = disenos;
	}
	
	

}
