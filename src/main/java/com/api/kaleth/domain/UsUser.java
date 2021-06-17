package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the us_user database table.
 * 
 */
@Entity
@Table(name="us_user")
@NamedQuery(name="UsUser.findAll", query="SELECT u FROM UsUser u")
public class UsUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_USUARIO")
	private Long idUsuario;

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

	//bi-directional many-to-one association to UsEstadocivil
	@ManyToOne
	@JoinColumn(name="ID_ESTADOCIVIL")
	private UsEstadocivil estadocivil;

	//bi-directional many-to-one association to UsGenero
	@ManyToOne
	@JoinColumn(name="ID_GENERO")
	private UsGenero genero;

	//bi-directional many-to-many association to UsRole
		@ManyToMany
		@JoinTable(
			name="us_rolesxusuarios"
			, joinColumns={
				@JoinColumn(name="ID_USUARIO")
				}
			, inverseJoinColumns={
				@JoinColumn(name="ID_ROLES")
				}
			)
		private Set<UsRole> roles = new HashSet<>();
		
	public UsUser() {
	}
	

	/*
	 * public UsUser(String apellido, String cedula, String direccion, String email,
	 * Boolean estado, Date fechanacimiento, String nombre, String password, String
	 * telefono) { super(); this.apellido = apellido; this.cedula = cedula;
	 * this.direccion = direccion; this.email = email; this.estado = estado;
	 * this.fechanacimiento = fechanacimiento; this.nombre = nombre; this.password =
	 * password; this.telefono = telefono; }
	 */
	
	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public UsUser(String apellido, String cedula, String direccion, String email, Boolean estado, Date fechanacimiento,
			String nombre, String nombreUsuario, String password, String telefono) {
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
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public UsEstadocivil getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(UsEstadocivil estadocivil) {
		this.estadocivil = estadocivil;
	}

	public UsGenero getGenero() {
		return genero;
	}

	public void setGenero(UsGenero genero) {
		this.genero = genero;
	}

	public Set<UsRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UsRole> roles) {
		this.roles = roles;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	 

}