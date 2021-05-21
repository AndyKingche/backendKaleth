package com.api.kaleth.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="id_usuarios")
	private Long id_usuarios;
	
	@Column(name="nombre", length=50)
	private String nombre;
	
	@Column(name="apellido", length=50)
	private String apellido;
	
	@Column(name="cedula",length=10)
	private String cedula;
	
	@Column(name="telefono",length=10)
	private String telefono;
	
	@Column(name="email",length=50)
	private String email;
	
	@Column(name="password",length=20)
	private String password;
	
	@Column(name="estado",length=10)
	private Boolean estado;
	
	@Column(name="fecha_nacimiento")
	private Date fecha_nacimiento;
	
	@Column(name="direccion")
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name = "genero_id")
	private Genero genero;
	
	@ManyToOne
	@JoinColumn(name = "estadocivil_id")
	
	private EstadoCivil estadocivil;

	public Usuarios() {
		
	}
	
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	

	public EstadoCivil getEstadocivil() {
		return estadocivil;
	}



	public void setEstadocivil(EstadoCivil estadocivil) {
		this.estadocivil = estadocivil;
	}

	public Long getId_usuarios() {
		return id_usuarios;
	}

	public void setId_usuarios(Long id_usuarios) {
		this.id_usuarios = id_usuarios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
		
	
}
