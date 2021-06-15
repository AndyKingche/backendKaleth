package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cat_stock database table.
 * 
 */
@Embeddable
public class CatStockPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_PRODUCTOS", insertable=false, updatable=false)
	private int idProductos;

	@Column(name="ID_PUNTOS_VENTA", insertable=false, updatable=false)
	private int idPuntosVenta;

	public CatStockPK() {
	}
	public int getIdProductos() {
		return this.idProductos;
	}
	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}
	public int getIdPuntosVenta() {
		return this.idPuntosVenta;
	}
	public void setIdPuntosVenta(int idPuntosVenta) {
		this.idPuntosVenta = idPuntosVenta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CatStockPK)) {
			return false;
		}
		CatStockPK castOther = (CatStockPK)other;
		return 
			(this.idProductos == castOther.idProductos)
			&& (this.idPuntosVenta == castOther.idPuntosVenta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idProductos;
		hash = hash * prime + this.idPuntosVenta;
		
		return hash;
	}
}