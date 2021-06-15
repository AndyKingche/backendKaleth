package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cat_disenos database table.
 * 
 */
@Entity
@Table(name="cat_disenos")
@NamedQuery(name="CatDiseno.findAll", query="SELECT c FROM CatDiseno c")
public class CatDiseno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_DISENOS")
	private Long idDisenos;

	private String nombre;

	//bi-directional many-to-one association to CatProducto
	@OneToMany(mappedBy="catDiseno")
	private List<CatProducto> catProductos;

	public CatDiseno() {
	}

	
	public Long getIdDisenos() {
		return idDisenos;
	}


	public void setIdDisenos(Long idDisenos) {
		this.idDisenos = idDisenos;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CatProducto> getCatProductos() {
		return this.catProductos;
	}

	public void setCatProductos(List<CatProducto> catProductos) {
		this.catProductos = catProductos;
	}

	public CatProducto addCatProducto(CatProducto catProducto) {
		getCatProductos().add(catProducto);
		catProducto.setCatDiseno(this);

		return catProducto;
	}

	public CatProducto removeCatProducto(CatProducto catProducto) {
		getCatProductos().remove(catProducto);
		catProducto.setCatDiseno(null);

		return catProducto;
	}

}