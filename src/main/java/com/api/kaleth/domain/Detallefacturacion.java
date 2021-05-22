package com.api.kaleth.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
@Table(name = "detalle_facturacion")
public class Detallefacturacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id_detallefac")
    private Long id_detallefac;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "subtotal")
    private Float subtotal;

    @Column(name = "total")
    private Float total;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Productos productos;

    @ManyToOne
    @JoinColumn(name = "facturacion")
    private Facturacion facturacion;
    
    public Detallefacturacion(){

    }
    public Detallefacturacion(Long id_detallefac, Date fecha, Integer cantidad, Float subtotal, Float total,
            Productos productos) {
                super();
        this.id_detallefac = id_detallefac;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.total = total;
        this.productos = productos;
    }
    public Long getId_detallefac() {
        return id_detallefac;
    }
    public void setId_detallefac(Long id_detallefac) {
        this.id_detallefac = id_detallefac;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Float getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }
    public Float getTotal() {
        return total;
    }
    public void setTotal(Float total) {
        this.total = total;
    }
    public Productos getProductos() {
        return productos;
    }
    public void setProductos(Productos productos) {
        this.productos = productos;
    }
    public Facturacion getFacturacion() {
        return facturacion;
    }
    public void setFacturacion(Facturacion facturacion) {
        this.facturacion = facturacion;
    }
    
    @Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Detallefacturacion detalle = (Detallefacturacion) o;
		if (detalle.id_detallefac == null || id_detallefac == null) {
			return false;
		}
		return Objects.equals(id_detallefac, detalle.id_detallefac);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id_detallefac);
	}
	

    @Override
	public String toString() {
		return "Detallefacturacion{" + "id=" + id_detallefac + ", productos='" + productos.getCategoria().getNombre() + "'" + ", descripcion='" + getTotal()+ "'" + '}';
	}
    
}
