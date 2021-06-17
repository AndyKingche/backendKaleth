package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cat_puntos_venta database table.
 * 
 */
@Entity
@Table(name="cat_puntos_venta")
@NamedQuery(name="CatPuntosVenta.findAll", query="SELECT c FROM CatPuntosVenta c")
public class CatPuntosVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_PUNTOS_VENTA")
	private Long idPuntosVenta;

	private String ciudad;

	private String direccion;

	@Column(name="NOMBRE_LOCAL")
	private String nombreLocal;

	private String telefono;


	public CatPuntosVenta() {
	}

	public Long getIdPuntosVenta() {
		return this.idPuntosVenta;
	}

	public void setIdPuntosVenta(Long idPuntosVenta) {
		this.idPuntosVenta = idPuntosVenta;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombreLocal() {
		return this.nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




}