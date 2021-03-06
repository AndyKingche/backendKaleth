package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * The persistent class for the cat_stock database table.
 * 
 */
@Entity
@Table(name="cat_stock")
@NamedQuery(name="CatStock.findAll", query="SELECT c FROM CatStock c")
public class CatStock implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private CatStockPK id;

	private float cantidad;

	private String existe;

	@Column(name="PRECIO_DISTRIBUIDOR")
	private float precioDistribuidor;

	@Column(name="PRECIO_MAYOR")
	private float precioMayor;

	@Column(name="PRECIO_UNIT")
	private float precioUnit;

	@Column(name="STOCK_MAX")
	private int stockMax;

	@Column(name="STOCK_MIN")
	private int stockMin;

	//bi-directional many-to-one association to CatProducto
	@ManyToOne
	@JoinColumn(name="ID_PRODUCTOS", insertable=false, updatable=false)
	private CatProducto catProducto;

	//bi-directional many-to-one association to CatPuntosVenta
	@ManyToOne
	@JoinColumn(name="ID_PUNTOS_VENTA", insertable=false, updatable=false)
	private CatPuntosVenta catPuntosVenta;



	public CatStock() {
	}

	public CatStockPK getId() {
		return this.id;
	}

	public void setId(CatStockPK id) {
		this.id = id;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public String getExiste() {
		return this.existe;
	}

	public void setExiste(String existe) {
		this.existe = existe;
	}



	public float getPrecioDistribuidor() {
		return precioDistribuidor;
	}

	public void setPrecioDistribuidor(float precioDistribuidor) {
		this.precioDistribuidor = precioDistribuidor;
	}

	public float getPrecioMayor() {
		return this.precioMayor;
	}

	public void setPrecioMayor(float precioMayor) {
		this.precioMayor = precioMayor;
	}

	public float getPrecioUnit() {
		return this.precioUnit;
	}

	public void setPrecioUnit(float precioUnit) {
		this.precioUnit = precioUnit;
	}

	public int getStockMax() {
		return this.stockMax;
	}

	public void setStockMax(int stockMax) {
		this.stockMax = stockMax;
	}

	public int getStockMin() {
		return this.stockMin;
	}

	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}

	public CatProducto getCatProducto() {
		return this.catProducto;
	}

	public void setCatProducto(CatProducto catProducto) {
		this.catProducto = catProducto;
	}

	public CatPuntosVenta getCatPuntosVenta() {
		return this.catPuntosVenta;
	}

	public void setCatPuntosVenta(CatPuntosVenta catPuntosVenta) {
		this.catPuntosVenta = catPuntosVenta;
	}


	
}