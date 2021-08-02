package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;



import com.sun.istack.NotNull;

import org.hibernate.annotations.GenericGenerator;


import java.util.List;


/**
 * The persistent class for the us_roles database table.
 * 
 */
@Entity
@Table(name="us_roles")

public class UsRole implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
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