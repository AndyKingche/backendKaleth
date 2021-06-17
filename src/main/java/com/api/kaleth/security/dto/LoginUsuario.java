package com.api.kaleth.security.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class LoginUsuario {

	
	private String email;

	private String password;
	
	public LoginUsuario() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}

