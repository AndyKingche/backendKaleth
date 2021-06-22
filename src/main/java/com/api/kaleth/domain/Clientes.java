package com.api.kaleth.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ven_cliente")
public class Clientes implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name = "id_cliente")
	private Long id_cliente;
	
	@Column(name="nombre_cli")
	private String nombrecliente;
	
	@Column(name="apellido_cli")
	private String apellidocliente;

	@Column(name="cedula_cli")
	private String cedulacliente;
	
	@Column(name="direccion_cli")
	private String direccioncliente;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="email")
	private String email;
	
	public Clientes() {
		// TODO Auto-generated constructor stub
	}

	public Clientes(Long id_cliente, String nombrecliente, String apellidocliente, String cedulacliente,
			String direccioncliente, String telefono, String email) {
		super();
		this.id_cliente = id_cliente;
		this.nombrecliente = nombrecliente;
		this.apellidocliente = apellidocliente;
		this.cedulacliente = cedulacliente;
		this.direccioncliente = direccioncliente;
		this.telefono = telefono;
		this.email = email;
	}
	
	
	
	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombrecliente() {
		return nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	public String getApellidocliente() {
		return apellidocliente;
	}

	public void setApellidocliente(String apellidocliente) {
		this.apellidocliente = apellidocliente;
	}

	public String getCedulacliente() {
		return cedulacliente;
	}

	public void setCedulacliente(String cedulacliente) {
		this.cedulacliente = cedulacliente;
	}

	public String getDireccioncliente() {
		return direccioncliente;
	}

	public void setDireccioncliente(String direccioncliente) {
		this.direccioncliente = direccioncliente;
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
	
	
	

}
