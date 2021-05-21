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
@Table(name="disenos")
public class Disenos implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator ="native")
	@GenericGenerator(name="native",strategy="native")
	@Column(name="id_disenos")
	private Long id_disenos;
	
	@Column(name="nombre")
	private String nombre;
	
	public Disenos() {
		
	}

	public Disenos(Long id_disenos, String nombre) {
		super();
		this.id_disenos = id_disenos;
		this.nombre = nombre;
	}
	
	

	public Long getId_disenos() {
		return id_disenos;
	}

	public void setId_disenos(Long id_disenos) {
		this.id_disenos = id_disenos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
