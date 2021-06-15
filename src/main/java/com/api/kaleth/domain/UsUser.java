package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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


	public UsUser() {
	}
	
	public Long getIdUsuario() {
		return idUsuario;
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

	/*
	 * public List<UsRole> getUsRoles() { return this.usRoles; }
	 * 
	 * public void setUsRoles(List<UsRole> usRoles) { this.usRoles = usRoles; }
	 * 
	 * public List<VenCabezaFactura> getVenCabezaFacturas() { return
	 * this.venCabezaFacturas; }
	 * 
	 * public void setVenCabezaFacturas(List<VenCabezaFactura> venCabezaFacturas) {
	 * this.venCabezaFacturas = venCabezaFacturas; }
	 * 
	 * public VenCabezaFactura addVenCabezaFactura(VenCabezaFactura
	 * venCabezaFactura) { getVenCabezaFacturas().add(venCabezaFactura);
	 * venCabezaFactura.setUsUser(this);
	 * 
	 * return venCabezaFactura; }
	 * 
	 * public VenCabezaFactura removeVenCabezaFactura(VenCabezaFactura
	 * venCabezaFactura) { getVenCabezaFacturas().remove(venCabezaFactura);
	 * venCabezaFactura.setUsUser(null);
	 * 
	 * return venCabezaFactura; }
	 */

}