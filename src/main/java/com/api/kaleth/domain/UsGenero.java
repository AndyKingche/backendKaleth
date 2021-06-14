package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the us_genero database table.
 * 
 */
@Entity
@Table(name="us_genero")
@NamedQuery(name="UsGenero.findAll", query="SELECT u FROM UsGenero u")
public class UsGenero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_GENERO")
	private int idGenero;

	private String genero;

	private String nombre;

	//bi-directional many-to-one association to UsUser
	@OneToMany(mappedBy="usGenero")
	private List<UsUser> usUsers;

	public UsGenero() {
	}

	public int getIdGenero() {
		return this.idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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
		usUser.setUsGenero(this);

		return usUser;
	}

	public UsUser removeUsUser(UsUser usUser) {
		getUsUsers().remove(usUser);
		usUser.setUsGenero(null);

		return usUser;
	}

}