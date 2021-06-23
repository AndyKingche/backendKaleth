package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * The persistent class for the ven_cliente database table.
 * 
 */
@Entity
@Table(name="ven_cliente")
@NamedQuery(name="VenCliente.findAll", query="SELECT v FROM VenCliente v")
public class VenCliente implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID_CLIENTE")
	private int idCliente;

	@Column(name="APELLIDO_CLI")
	private String apellidoCli;

	@Column(name="CEDULA_CLI")
	private String cedulaCli;

	@Column(name="DIRECCION_CLI")
	private String direccionCli;

	private String email;

	@Column(name="NOMBRE_CLI")
	private String nombreCli;

	private String telefono;

	//bi-directional many-to-one association to VenCabezaFactura
	@OneToMany(mappedBy="venCliente")
	private List<VenCabezaFactura> venCabezaFacturas;

	public VenCliente() {
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getApellidoCli() {
		return this.apellidoCli;
	}

	public void setApellidoCli(String apellidoCli) {
		this.apellidoCli = apellidoCli;
	}

	public String getCedulaCli() {
		return this.cedulaCli;
	}

	public void setCedulaCli(String cedulaCli) {
		this.cedulaCli = cedulaCli;
	}

	public String getDireccionCli() {
		return this.direccionCli;
	}

	public void setDireccionCli(String direccionCli) {
		this.direccionCli = direccionCli;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreCli() {
		return this.nombreCli;
	}

	public void setNombreCli(String nombreCli) {
		this.nombreCli = nombreCli;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<VenCabezaFactura> getVenCabezaFacturas() {
		return this.venCabezaFacturas;
	}

	public void setVenCabezaFacturas(List<VenCabezaFactura> venCabezaFacturas) {
		this.venCabezaFacturas = venCabezaFacturas;
	}

	public VenCabezaFactura addVenCabezaFactura(VenCabezaFactura venCabezaFactura) {
		getVenCabezaFacturas().add(venCabezaFactura);
		venCabezaFactura.setVenCliente(this);

		return venCabezaFactura;
	}

	public VenCabezaFactura removeVenCabezaFactura(VenCabezaFactura venCabezaFactura) {
		getVenCabezaFacturas().remove(venCabezaFactura);
		venCabezaFactura.setVenCliente(null);

		return venCabezaFactura;
	}

}