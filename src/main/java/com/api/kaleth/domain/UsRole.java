package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the us_roles database table.
 * 
 */
@Entity
@Table(name="us_roles")
@NamedQuery(name="UsRole.findAll", query="SELECT u FROM UsRole u")
public class UsRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_ROLES")
	private Long idRoles;

	private String descripcion;

	private String nombre;

	private String rol;

	

	public UsRole() {
	}

	public Long getIdRoles() {
		return idRoles;
	}

	public void setIdRoles(Long idRoles) {
		this.idRoles = idRoles;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	
}