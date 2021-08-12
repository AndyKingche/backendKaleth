package com.api.kaleth.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cat_parametros")
public class catParametros implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID_PARAMETROS")
	private Long idParametros;
	
	private String textoBanner;
	
	private String mensajePuntosVenta;
	
	private String fraseFooter;
	
	private String tituloServicios;
	
	private String servicio1;
	private String servicio2;
	private String servicio3;
	private String servicio4;
	private String servicio5;
	
	private String tituloInformacion;
	
	private String telefono;
	
	private String celular;
	
	private String correo1;
	private String correo2;
	
	private String direccion;
	
	@Column(columnDefinition = "LONGTEXT")
	private String urlFotoBanner1;
	@Column(columnDefinition = "LONGTEXT")
	private String urlFotoBanner2;
	@Column(columnDefinition = "LONGTEXT")
	private String urlFotoBanner3;
	public Long getIdParametros() {
		return idParametros;
	}
	public void setIdParametros(Long idParametros) {
		this.idParametros = idParametros;
	}
	public String getTextoBanner() {
		return textoBanner;
	}
	public void setTextoBanner(String textoBanner) {
		this.textoBanner = textoBanner;
	}
	public String getMensajePuntosVenta() {
		return mensajePuntosVenta;
	}
	public void setMensajePuntosVenta(String mensajePuntosVenta) {
		this.mensajePuntosVenta = mensajePuntosVenta;
	}
	public String getFraseFooter() {
		return fraseFooter;
	}
	public void setFraseFooter(String fraseFooter) {
		this.fraseFooter = fraseFooter;
	}
	public String getTituloServicios() {
		return tituloServicios;
	}
	public void setTituloServicios(String tituloServicios) {
		this.tituloServicios = tituloServicios;
	}
	public String getServicio1() {
		return servicio1;
	}
	public void setServicio1(String servicio1) {
		this.servicio1 = servicio1;
	}
	public String getServicio2() {
		return servicio2;
	}
	public void setServicio2(String servicio2) {
		this.servicio2 = servicio2;
	}
	public String getServicio3() {
		return servicio3;
	}
	public void setServicio3(String servicio3) {
		this.servicio3 = servicio3;
	}
	public String getServicio4() {
		return servicio4;
	}
	public void setServicio4(String servicio4) {
		this.servicio4 = servicio4;
	}
	public String getServicio5() {
		return servicio5;
	}
	public void setServicio5(String servicio5) {
		this.servicio5 = servicio5;
	}
	public String getTituloInformacion() {
		return tituloInformacion;
	}
	public void setTituloInformacion(String tituloInformacion) {
		this.tituloInformacion = tituloInformacion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCorreo1() {
		return correo1;
	}
	public void setCorreo1(String correo1) {
		this.correo1 = correo1;
	}
	public String getCorreo2() {
		return correo2;
	}
	public void setCorreo2(String correo2) {
		this.correo2 = correo2;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getUrlFotoBanner1() {
		return urlFotoBanner1;
	}
	public void setUrlFotoBanner1(String urlFotoBanner1) {
		this.urlFotoBanner1 = urlFotoBanner1;
	}
	public String getUrlFotoBanner2() {
		return urlFotoBanner2;
	}
	public void setUrlFotoBanner2(String urlFotoBanner2) {
		this.urlFotoBanner2 = urlFotoBanner2;
	}
	public String getUrlFotoBanner3() {
		return urlFotoBanner3;
	}
	public void setUrlFotoBanner3(String urlFotoBanner3) {
		this.urlFotoBanner3 = urlFotoBanner3;
	}
	

	
	

}
