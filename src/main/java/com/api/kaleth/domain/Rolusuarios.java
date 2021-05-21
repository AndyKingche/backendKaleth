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
@Table(name="rolusuarios")
public class Rolusuarios implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="id_rolusuarios")
	private Long id_rolusuarios;
	
	@ManyToOne
	@JoinColumn(name="rol_id")
	private Roles rol;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuarios user;
	
	public Rolusuarios() {
		
	}
	public Long getId_rolusuarios() {
		return id_rolusuarios;
	}

	public void setId_rolusuarios(Long id_rolusuarios) {
		this.id_rolusuarios = id_rolusuarios;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}
	
}
