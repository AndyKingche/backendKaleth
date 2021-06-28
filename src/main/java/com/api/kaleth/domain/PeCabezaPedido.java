package com.api.kaleth.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="pe_cabeza_pedido")
public class PeCabezaPedido implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID_CABEZA_PE")
	private Long idCabezaPe;

	private String estado;
	

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_PE")
	private Date fechaPe;

	private float total;

	@OneToMany(mappedBy = "peCabezaPedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<PeDetallePedido> detallepedido = new TreeSet<>();

	//bi-directional many-to-one association to VenCliente
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE")
	private VenCliente venCliente;

	public Long getIdCabezaPe() {
		return idCabezaPe;
	}

	public void setIdCabezaPe(Long idCabezaPe) {
		this.idCabezaPe = idCabezaPe;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaPe() {
		return fechaPe;
	}

	public void setFechaPe(Date fechaPe) {
		this.fechaPe = fechaPe;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public VenCliente getVenCliente() {
		return venCliente;
	}

	public void setVenCliente(VenCliente venCliente) {
		this.venCliente = venCliente;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PeCabezaPedido pedidoCabeza = (PeCabezaPedido) o;
		if (pedidoCabeza.idCabezaPe == null || idCabezaPe == null) {
			return false;
		}
		return Objects.equals(idCabezaPe, pedidoCabeza.idCabezaPe);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(idCabezaPe);
	}
	
	@Override
	public String toString() {
		return "PeCabezaPedido{" + "id=" + idCabezaPe + ", estado='" + estado + "'" + ", fechaa='" + fechaPe+ "'" + '}';
	}

	public Set<PeDetallePedido> getDetallepedido() {
		return detallepedido;
	}

	public void setDetallepedido(Set<PeDetallePedido> detallepedido) {
		//detallefact.forEach(x -> x.setVenCabezaFactura(this));
		//this.detallefact = detallefact;
		detallepedido.forEach(x-> x.setPeCabezaPedido(this));
		this.detallepedido = detallepedido;
	}
	
	
	
}
