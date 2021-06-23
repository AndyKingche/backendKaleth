package com.api.kaleth.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


/**
 * The persistent class for the ven_cabeza_factura database table.
 * 
 */
@Entity
@Table(name="ven_cabeza_factura")
//@NamedQuery(name="VenCabezaFactura.findAll", query="SELECT v FROM VenCabezaFactura v")
public class VenCabezaFactura implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID_CABEZA_FAC")
	private Long idCabezaFac;

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

	

	
	@OneToMany(mappedBy = "venCabezaFactura", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<VenDetalleFactura> detallefact = new TreeSet<>();

	//bi-directional many-to-one association to VenCliente
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE")
	
	private VenCliente venCliente;
	
	public VenCabezaFactura() {
	}

	

	public Long getIdCabezaFac() {
		return idCabezaFac;
	}



	public void setIdCabezaFac(Long idCabezaFac) {
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

	
	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		VenCabezaFactura cabeceraFactura = (VenCabezaFactura) o;
		if (cabeceraFactura.idCabezaFac == null || idCabezaFac == null) {
			return false;
		}
		return Objects.equals(idCabezaFac, cabeceraFactura.idCabezaFac);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(idCabezaFac);
	}
	
	@Override
	public String toString() {
		return "VenCabezaFactura{" + "id=" + idCabezaFac + ", estado='" + estado + "'" + ", fecha='" + fechaFactu + "'" + '}';
	}
	
	
	
	public Set<VenDetalleFactura> getDetallefact() {
		return detallefact;
	}

	public void setDetallefact(Set<VenDetalleFactura> detallefact) {
		detallefact.forEach(x -> x.setVenCabezaFactura(this));
		this.detallefact = detallefact;
	}


}