package com.api.kaleth.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


public class UsRoleUserPk implements Serializable{

	
	@GeneratedValue(generator = "native")
	@GenericGenerator(name = "native",strategy = "native")
	@Column(name="ID_USUARIO", insertable=false, updatable=false)
	private Long idUsuario;

	@Column(name="ID_ROLES", insertable=false, updatable=false)
	private Long idRoles;
	
	public UsRoleUserPk() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdRoles() {
		return idRoles;
	}

	public void setIdRoles(Long idRoles) {
		this.idRoles = idRoles;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CatStockPK)) {
			return false;
		}
		UsRoleUserPk castOther = (UsRoleUserPk)other;
		return 
			(this.idUsuario == castOther.idUsuario)
			&& (this.idRoles == castOther.idRoles);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = (int)(hash * prime + this.idUsuario);
		hash = (int)(hash * prime + this.idRoles);
		
		return hash;
	}
}
