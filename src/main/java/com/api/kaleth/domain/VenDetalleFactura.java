package com.api.kaleth.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the ven_detalle_factura database table.
 * 
 */
@Entity
@Table(name="ven_detalle_factura")
@NamedQuery(name="VenDetalleFactura.findAll", query="SELECT v FROM VenDetalleFactura v")
public class VenDetalleFactura implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID_DETALLE_FACT")
	private Long idDetalleFact;

	@Column(name="CANTIDAD_FACT")
	private int cantidadFact;

	private String descripcion;

	@Column(name="VALOR_TOTAL")
	private float valorTotal;

	@Column(name="VALOR_UNIT")
	private float valorUnit;

	//bi-directional many-to-one association to CatStock
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CAT_ID_PRODUCTOS", referencedColumnName="ID_PRODUCTOS"),
		@JoinColumn(name="CAT_ID_PUNTOS_VENTA", referencedColumnName="ID_PUNTOS_VENTA")
		})
	private CatStock catStock;

	//bi-directional many-to-one association to VenCabezaFactura
	@ManyToOne
	@JoinColumn(name="ID_CABEZA_FAC")
	@JsonIgnore
	private VenCabezaFactura venCabezaFactura;
	


	public VenDetalleFactura() {
	}

	

	public Long getIdDetalleFact() {
		return idDetalleFact;
	}



	public void setIdDetalleFact(Long idDetalleFact) {
		this.idDetalleFact = idDetalleFact;
	}



	public int getCantidadFact() {
		return this.cantidadFact;
	}

	public void setCantidadFact(int cantidadFact) {
		this.cantidadFact = cantidadFact;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float getValorUnit() {
		return this.valorUnit;
	}

	public void setValorUnit(float valorUnit) {
		this.valorUnit = valorUnit;
	}

	public CatStock getCatStock() {
		return this.catStock;
	}

	public void setCatStock(CatStock catStock) {
		this.catStock = catStock;
	}

	public VenCabezaFactura getVenCabezaFactura() {
		return this.venCabezaFactura;
	}

	public void setVenCabezaFactura(VenCabezaFactura venCabezaFactura) {
		this.venCabezaFactura = venCabezaFactura;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		VenDetalleFactura detallefact = (VenDetalleFactura) o;
		if (detallefact.idDetalleFact == null || idDetalleFact == null) {
			return false;
		}
		return Objects.equals(idDetalleFact, detallefact.idDetalleFact);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idDetalleFact);
	}
	
	@Override
	public String toString() {
		return "DetallFact{" + "id=" + idDetalleFact + ", cantidad='" + cantidadFact + "'" + ", categoria='" + catStock.getCatProducto().getCatCategoria().getNombreCategoria()+ "'" + '}';
	}

}