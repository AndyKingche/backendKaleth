package com.api.kaleth.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JWTDTO {
	private String token;
	private String bearer = "Bearer";
	private String nombreUsaurio;
	
	private Collection<? extends GrantedAuthority> authorities;

	public JWTDTO(String token, String nombreUsaurio, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.nombreUsaurio = nombreUsaurio;
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	

	public String getNombreUsaurio() {
		return nombreUsaurio;
	}

	public void setNombreUsaurio(String nombreUsaurio) {
		this.nombreUsaurio = nombreUsaurio;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	

}
