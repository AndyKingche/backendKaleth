package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ven_cabeza_factura database table.
 * 
 */
@Entity
@Table(name="ven_cabeza_factura")
@NamedQuery(name="VenCabezaFactura.findAll", query="SELECT v FROM VenCabezaFactura v")
public class VenCabezaFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_CABEZA_FAC")
	private int idCabezaFac;

	private String estado;
	
	@Column(name="iva")
	private float iva;
	

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FACTU")
	private Date fechaFactu;

	private float total;

	//bi-directional many-to-one association to UsUser
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private UsUser usUser;

	//bi-directional many-to-one association to VenCliente
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE")
	private VenCliente venCliente;

	//bi-directional many-to-one association to VenDetalleFactura
	@OneToMany(mappedBy="venCabezaFactura")
	private List<VenDetalleFactura> venDetalleFacturas;

	public VenCabezaFactura() {
	}

	public int getIdCabezaFac() {
		return this.idCabezaFac;
	}

	public void setIdCabezaFac(int idCabezaFac) {
		this.idCabezaFac = idCabezaFac;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaFactu() {
		return this.fechaFactu;
	}

	public void setFechaFactu(Date fechaFactu) {
		this.fechaFactu = fechaFactu;
	}

	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public UsUser getUsUser() {
		return this.usUser;
	}

	public void setUsUser(UsUser usUser) {
		this.usUser = usUser;
	}

	public VenCliente getVenCliente() {
		return this.venCliente;
	}

	public void setVenCliente(VenCliente venCliente) {
		this.venCliente = venCliente;
	}

	public List<VenDetalleFactura> getVenDetalleFacturas() {
		return this.venDetalleFacturas;
	}

	public void setVenDetalleFacturas(List<VenDetalleFactura> venDetalleFacturas) {
		this.venDetalleFacturas = venDetalleFacturas;
	}

	public VenDetalleFactura addVenDetalleFactura(VenDetalleFactura venDetalleFactura) {
		getVenDetalleFacturas().add(venDetalleFactura);
		venDetalleFactura.setVenCabezaFactura(this);

		return venDetalleFactura;
	}

	public VenDetalleFactura removeVenDetalleFactura(VenDetalleFactura venDetalleFactura) {
		getVenDetalleFacturas().remove(venDetalleFactura);
		venDetalleFactura.setVenCabezaFactura(null);

		return venDetalleFactura;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

}