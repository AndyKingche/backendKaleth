package com.api.kaleth.respository;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.VenCabezaFactura;

@RestController
@RequestMapping("/api")
public interface FacturacionRepository extends JpaRepository<VenCabezaFactura,Long>{
	@Query(value="SELECT DISTINCTROW(cabe.id_cabeza_fac), cabe.* , cli.*, us.*, pv.* FROM ven_cabeza_factura cabe, ven_cliente cli, us_user us, ven_detalle_factura deta, cat_stock st, cat_puntos_venta pv WHERE cabe.id_usuario=us.id_usuario AND cabe.id_cliente= cli.id_cliente AND cabe.id_cabeza_fac=deta.id_cabeza_fac AND deta.cat_id_puntos_venta= pv.id_puntos_venta AND cabe.estado='A' AND (cabe.fecha_factu BETWEEN :fechainicio  AND  :fechafin )", nativeQuery = true)
	List<VenCabezaFactura> facturaFechas (@Param("fechainicio") Date fechainicio, @Param("fechafin")Date fechafin);
}