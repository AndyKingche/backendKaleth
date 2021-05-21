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
@Table(name="inventario_quito")
public class InventarioQuito implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="id_iquito")
	private Long id_iquito;
	
	@Column(name="stock_min")
	private Integer stock_min;
	
	@Column(name="stock_total")
	private Integer stock_total;
	
	@Column(name="precio_unitario")
	private Float precio_unitario;
	
	@Column(name="precio_mayor")
	private Float precio_mayor;
	
	@Column(name="precio_distribuidor")
	private Float precio_distribuidor;
	
	@ManyToOne
	@JoinColumn(name="producto_id", unique = true)
	private Productos productos;
	
	public InventarioQuito() {
		
		
	}

	public Long getId_iquito() {
		return id_iquito;
	}

	public void setId_iquito(Long id_iquito) {
		this.id_iquito = id_iquito;
	}

	public Integer getStock_min() {
		return stock_min;
	}

	public void setStock_min(Integer stock_min) {
		this.stock_min = stock_min;
	}

	public Integer getStock_total() {
		return stock_total;
	}

	public void setStock_total(Integer stock_total) {
		this.stock_total = stock_total;
	}

	public Float getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(Float precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public Float getPrecio_mayor() {
		return precio_mayor;
	}

	public void setPrecio_mayor(Float precio_mayor) {
		this.precio_mayor = precio_mayor;
	}

	public Float getPrecio_distribuidor() {
		return precio_distribuidor;
	}

	public void setPrecio_distribuidor(Float precio_distribuidor) {
		this.precio_distribuidor = precio_distribuidor;
	}

	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}
	
	
}
