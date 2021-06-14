package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the us_estadocivil database table.
 * 
 */
@Entity
@Table(name="us_estadocivil")
@NamedQuery(name="UsEstadocivil.findAll", query="SELECT u FROM UsEstadocivil u")
public class UsEstadocivil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_ESTADOCIVIL")
	private int idEstadocivil;

	private String nombre;

	//bi-directional many-to-one association to UsUser
	@OneToMany(mappedBy="usEstadocivil")
	private List<UsUser> usUsers;

	public UsEstadocivil() {
	}

	public int getIdEstadocivil() {
		return this.idEstadocivil;
	}

	public void setIdEstadocivil(int idEstadocivil) {
		this.idEstadocivil = idEstadocivil;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<UsUser> getUsUsers() {
		return this.usUsers;
	}

	public void setUsUsers(List<UsUser> usUsers) {
		this.usUsers = usUsers;
	}

	public UsUser addUsUser(UsUser usUser) {
		getUsUsers().add(usUser);
		usUser.setUsEstadocivil(this);

		return usUser;
	}

	public UsUser removeUsUser(UsUser usUser) {
		getUsUsers().remove(usUser);
		usUser.setUsEstadocivil(null);

		return usUser;
	}

}