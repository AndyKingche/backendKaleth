package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the ven_detalle_factura database table.
 * 
 */
@Entity
@Table(name="ven_detalle_factura")
@NamedQuery(name="VenDetalleFactura.findAll", query="SELECT v FROM VenDetalleFactura v")
public class VenDetalleFactura implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID_DETALLE_FACT")
	private int idDetalleFact;

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
	private VenCabezaFactura venCabezaFactura;

	public VenDetalleFactura() {
	}

	public int getIdDetalleFact() {
		return this.idDetalleFact;
	}

	public void setIdDetalleFact(int idDetalleFact) {
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

}