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
	private Long idProductos;

	@Column(name="ID_PUNTOS_VENTA", insertable=false, updatable=false)
	private Long idPuntosVenta;

	public CatStockPK() {
	}
	

	public Long getIdProductos() {
		return idProductos;
	}


	public void setIdProductos(Long idProductos) {
		this.idProductos = idProductos;
	}


	public Long getIdPuntosVenta() {
		return idPuntosVenta;
	}


	public void setIdPuntosVenta(Long idPuntosVenta) {
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
		int hash =  17;
		hash = (int) (hash * prime + this.idProductos);
		hash = (int) (hash * prime + this.idPuntosVenta);
		
		return hash;
	}
}