package com.api.kaleth.security.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.kaleth.domain.UsUser;

public class UsuarioPrincipal implements UserDetails {

	
	private String apellido;

	private String cedula;

	private String direccion;

	private String email;

	private Boolean estado;

	@Temporal(TemporalType.DATE)
	private Date fechanacimiento;

	private String nombre;
	
	private String nombreUsuario;

	private String password;

	private String telefono;
	
	private Collection<?extends GrantedAuthority> autorities;
	
	public UsuarioPrincipal() {
		// TODO Auto-generated constructor stub
	}

	
	public UsuarioPrincipal(String apellido, String cedula, String direccion, String email, Boolean estado,
			Date fechanacimiento, String nombre, String nombreUsuario,String password, String telefono,
			Collection<? extends GrantedAuthority> autorities) {
		super();
		this.apellido = apellido;
		this.cedula = cedula;
		this.direccion = direccion;
		this.email = email;
		this.estado = estado;
		this.fechanacimiento = fechanacimiento;
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.telefono = telefono;
		this.autorities = autorities;
	}

	
	public static UsuarioPrincipal build(UsUser usuario) {
		List<GrantedAuthority> authorities = 
				usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRol())).collect(Collectors.toList());
		
				return new UsuarioPrincipal(usuario.getApellido(),usuario.getCedula(),
				usuario.getDireccion(),usuario.getEmail(),usuario.getEstado()
				,usuario.getFechanacimiento(),usuario.getNombre(),usuario.getNombreUsuario(),usuario.getPassword()
				,usuario.getTelefono(),authorities);
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Collection<? extends GrantedAuthority> getAutorities() {
		return autorities;
	}

	public void setAutorities(Collection<? extends GrantedAuthority> autorities) {
		this.autorities = autorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return autorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nombreUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	
}
