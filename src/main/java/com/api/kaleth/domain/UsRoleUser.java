package com.api.kaleth.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


public class UsRoleUser implements Serializable {
	
	
	@EmbeddedId
	//private UsRoleUserPk id;
	
	//bi-directional many-to-one association to CatProducto
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", insertable=false, updatable=false)
	private UsUser usUser;

	//bi-directional many-to-one association to CatPuntosVenta
	@ManyToOne
	@JoinColumn(name="ID_ROLES", insertable=false, updatable=false)
	private UsRole usRole;
		
	public UsRoleUser() {
			// TODO Auto-generated constructor stub
	}

	/*
	 * public UsRoleUserPk getId() { return id; }
	 * 
	 * public void setId(UsRoleUserPk id) { this.id = id; }
	 */

	public UsUser getUsUser() {
			return usUser;
	}

	public void setUsUser(UsUser usUser) {
			this.usUser = usUser;
	}

	public UsRole getUsRole() {
			return usRole;
	}

	public void setUsRole(UsRole usRole) {
			this.usRole = usRole;
	}
		
}
