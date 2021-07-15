package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * The persistent class for the cat_disenos database table.
 * 
 */
@Entity
@Table(name="cat_disenos")
@NamedQuery(name="CatDiseno.findAll", query="SELECT c FROM CatDiseno c")
public class CatDiseno implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID_DISENOS")
	private Long idDisenos;

	private String nombre;

	@Column(name="URL_FOTO", unique=true, columnDefinition = "LONGTEXT")
	private String urlFoto;
	
	public CatDiseno() {
	}

	
	public Long getIdDisenos() {
		return idDisenos;
	}


	public void setIdDisenos(Long idDisenos) {
		this.idDisenos = idDisenos;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getUrlFoto() {
		return urlFoto;
	}


	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	


}