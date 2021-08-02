package com.api.kaleth.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pe_detalle_pedido")
public class PeDetallePedido implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID_DETALLE_PE")
	private Long idDetallePe;

	@Column(name="CANTIDAD_PE")
	private int cantidadPe;

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
	private PeCabezaPedido peCabezaPedido;

	public Long getIdDetallePe() {
		return idDetallePe;
	}

	public void setIdDetallePe(Long idDetallePe) {
		this.idDetallePe = idDetallePe;
	}

	public int getCantidadPe() {
		return cantidadPe;
	}

	public void setCantidadPe(int cantidadPe) {
		this.cantidadPe = cantidadPe;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(float valorUnit) {
		this.valorUnit = valorUnit;
	}

	public CatStock getCatStock() {
		return catStock;
	}

	public void setCatStock(CatStock catStock) {
		this.catStock = catStock;
	}

	public PeCabezaPedido getPeCabezaPedido() {
		return peCabezaPedido;
	}

	public void setPeCabezaPedido(PeCabezaPedido peCabezaPedido) {
		this.peCabezaPedido = peCabezaPedido;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PeDetallePedido detallepedido = (PeDetallePedido) o;
		if (detallepedido.idDetallePe == null || detallepedido == null) {
			return false;
		}
		return Objects.equals(idDetallePe, detallepedido.idDetallePe);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idDetallePe);
	}
	
	@Override
	public String toString() {
		return "DetallPedido{" + "id=" + 
	idDetallePe + ", cantidadFact='" + cantidadPe + "'" + ", precioUnit"+valorUnit+ ", descripcion"+descripcion+", catStock={'" 
+"idproductos"+catStock.getId().getIdProductos()+"idpuntosventa"+catStock.getId().getIdPuntosVenta()+"}"+ "'" + '}';
	}
}
