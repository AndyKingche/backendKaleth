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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.jsf.FacesContextUtils;

@Entity
@Table(name="facturacion")
public class Facturacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id_facturacion")
    private Long id_facturacion;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "iva")
    private Float iva;

    @ManyToOne
    @JoinColumn(name="usuarios_id")
    private Usuarios usuarios;

    @OneToMany(mappedBy = "facturacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    private Set<Detallefacturacion> detalle = new TreeSet<>();

    public Facturacion(){

    }

   

    public Facturacion(Long id_facturacion, Date fecha, Float iva, Usuarios usuarios) {
        this.id_facturacion = id_facturacion;
        this.fecha = fecha;
        this.iva = iva;
        this.usuarios = usuarios;
    }



    public Long getId_facturacion() {
        return id_facturacion;
    }

    public void setId_facturacion(Long id_facturacion) {
        this.id_facturacion = id_facturacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    
   

    public Usuarios getUsuarios() {
        return usuarios;
    }



    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }



    @Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Facturacion facturacion = (Facturacion) o;
		if (facturacion.id_facturacion == null || id_facturacion == null) {
			return false;
		}
		return Objects.equals(id_facturacion, facturacion.id_facturacion);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id_facturacion);
	}
	
	@Override
	public String toString() {
		return "Facturacion{" + "id_facturacion=" + id_facturacion + ", fecha='" + fecha + "'" + '}';
	}

    public Set<Detallefacturacion> getDetalle() {
		return detalle;
	}
	
	public void setDetalle(Set<Detallefacturacion> detalle) {
		detalle.forEach(x -> x.setFacturacion(this));
		this.detalle = detalle;
	}
	

    
    
    

    

}
