package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cat_tallas database table.
 * 
 */
@Entity
@Table(name="cat_tallas")
@NamedQuery(name="CatTalla.findAll", query="SELECT c FROM CatTalla c")
public class CatTalla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_TALLAS")
	private int idTallas;

	private String descripcion;

	private String medida;

	private String tipo;

	//bi-directional many-to-one association to CatProducto
	@OneToMany(mappedBy="catTalla")
	private List<CatProducto> catProductos;

	public CatTalla() {
	}

	public int getIdTallas() {
		return this.idTallas;
	}

	public void setIdTallas(int idTallas) {
		this.idTallas = idTallas;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMedida() {
		return this.medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<CatProducto> getCatProductos() {
		return this.catProductos;
	}

	public void setCatProductos(List<CatProducto> catProductos) {
		this.catProductos = catProductos;
	}

	public CatProducto addCatProducto(CatProducto catProducto) {
		getCatProductos().add(catProducto);
		catProducto.setCatTalla(this);

		return catProducto;
	}

	public CatProducto removeCatProducto(CatProducto catProducto) {
		getCatProductos().remove(catProducto);
		catProducto.setCatTalla(null);

		return catProducto;
	}

}