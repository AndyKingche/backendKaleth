package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cat_productos database table.
 * 
 */
@Entity
@Table(name="cat_productos")
@NamedQuery(name="CatProducto.findAll", query="SELECT c FROM CatProducto c")
public class CatProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_PRODUCTOS")
	private Long idProductos;

	@Column(name="COD_PRODUCTO", unique=true)
	private Long codProducto;
	
	//bi-directional many-to-one association to CatCategoria
	@ManyToOne
	@JoinColumn(name="ID_CATEGORIA")
	private CatCategoria catCategoria;

	//bi-directional many-to-one association to CatDiseno
	@ManyToOne
	@JoinColumn(name="ID_DISENOS")
	private CatDiseno catDiseno;

	//bi-directional many-to-one association to CatTalla
	@ManyToOne
	@JoinColumn(name="ID_TALLAS")
	private CatTalla catTalla;

	
	public CatProducto() {
	}

	public Long getIdProductos() {
		return this.idProductos;
	}

	public void setIdProductos(Long idProductos) {
		this.idProductos = idProductos;
	}

	public CatCategoria getCatCategoria() {
		return this.catCategoria;
	}

	public void setCatCategoria(CatCategoria catCategoria) {
		this.catCategoria = catCategoria;
	}

	public CatDiseno getCatDiseno() {
		return this.catDiseno;
	}

	public void setCatDiseno(CatDiseno catDiseno) {
		this.catDiseno = catDiseno;
	}

	public CatTalla getCatTalla() {
		return this.catTalla;
	}

	public void setCatTalla(CatTalla catTalla) {
		this.catTalla = catTalla;
	}

	public Long getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Long codProducto) {
		this.codProducto = codProducto;
	}




}