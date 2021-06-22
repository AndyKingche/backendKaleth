package com.api.kaleth.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.CatStock;


@RestController
@RequestMapping("/api")
public interface cat_stockRepository extends JpaRepository<CatStock, Long>{

	@Query(value="SELECT cantidad FROM cat_stock WHERE id_productos=? and id_puntos_venta=?", nativeQuery = true)
	int encontrarStock(Integer id_producto,Integer id_puntoventa);
	
	@Query(value="UPDATE cat_stock SET cantidad=? WHERE id_productos=? and id_puntos_venta=?", nativeQuery = true)
	int actualizarStock(Integer cantidad_actualizada, Integer id_producto,Integer id_puntoventa);
	
	
	@Query(value="UPDATE cat_stock SET cantidad=?, precio_unit=?, precio_mayor=?, precio_distribuidor=?, stock_max=?, stock_min=? WHERE id_productos=? and id_puntos_venta=?", nativeQuery = true)
	int actualizarStocks(Integer cantidad_actualizada, 
			Float precioUnit,
			Float precioMay,
			Float precioDist,
			Integer stockMax,
			Integer stockMin,
			Integer id_producto,
			Integer id_puntoventa);
	
	@Query(value="SELECT * FROM cat_stock s WHERE s.id_productos=? AND s.id_puntos_venta=?",nativeQuery = true)
	List<CatStock> findbyIdProductoIdPuntosVenta(Integer id_producto,Integer id_puntoventa);

	

}
