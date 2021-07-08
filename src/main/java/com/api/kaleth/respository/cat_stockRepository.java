package com.api.kaleth.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.CatStock;

@RestController
@RequestMapping("/api")
public interface cat_stockRepository extends JpaRepository<CatStock, Long> {

	@Query(value = "SELECT cantidad FROM cat_stock WHERE id_productos=? and id_puntos_venta=?", nativeQuery = true)
	int encontrarStock(Integer id_producto, Integer id_puntoventa);

	@Query(value = "UPDATE cat_stock SET cantidad=? WHERE id_productos=? and id_puntos_venta=?", nativeQuery = true)
	int actualizarStockRestar(Integer cantidad_actualizada, Integer id_producto, Integer id_puntoventa);

	@Query(value = "UPDATE cat_stock SET cantidad=?, precio_unit=?, precio_mayor=?, precio_distribuidor=?, stock_max=?, stock_min=? WHERE id_productos=? and id_puntos_venta=?", nativeQuery = true)
	int actualizarStocks(Integer cantidad_actualizada, Float precioUnit, Float precioMay, Float precioDist,
			Integer stockMax, Integer stockMin, Integer id_producto, Integer id_puntoventa);

	@Query(value = "SELECT * FROM cat_stock s WHERE s.id_productos=? AND s.id_puntos_venta=?", nativeQuery = true)
	List<CatStock> findbyIdProductoIdPuntosVenta(Integer id_producto, Integer id_puntoventa);

	@Query(value = "SELECT * FROM cat_stock s , cat_productos p   WHERE p.cod_producto=? AND s.id_puntos_venta=? AND s.id_productos=p.id_productos AND existe='S'", nativeQuery = true)
	List<CatStock> findProductoStockby_codProducto(Integer codigo_producto, Integer id_puntoventa);

	@Query(value = "SELECT * FROM cat_stock s  WHERE s.existe='S'  LIMIT ?,?", nativeQuery = true)
	List<CatStock> findAllExitents(Integer incio, Integer numeroFilas);

	@Query(value = "SELECT COUNT(*) FROM cat_stock s  WHERE s.existe='S' ", nativeQuery = true)
	int findCantidadAllExitents();


	// consultas reportes administrador

	@Query(value = "SELECT * FROM cat_stock s, cat_productos pr, cat_puntos_venta pv, cat_categoria cat, cat_disenos di,cat_tallas ta   WHERE s.id_productos=pr.id_productos AND s.id_puntos_venta=pv.id_puntos_venta AND pr.id_categoria=cat.id_categoria AND pr.id_disenos=di.id_disenos AND pr.id_tallas= ta.id_tallas AND s.existe='S' ORDER BY (pr.cod_producto );", nativeQuery = true)
	List<CatStock> findInventario();

	// consulta inventario de acuerdo al local que se encuentra
	@Query(value = "SELECT * FROM cat_stock s, cat_productos pr, cat_puntos_venta pv, cat_categoria cat, cat_disenos di,cat_tallas ta   WHERE s.id_productos=pr.id_productos AND s.id_puntos_venta=pv.id_puntos_venta AND pr.id_categoria=cat.id_categoria AND pr.id_disenos=di.id_disenos AND pr.id_tallas= ta.id_tallas AND s.existe='S' AND pv.id_puntos_venta=? ORDER BY (pr.cod_producto );", nativeQuery = true)
	List<CatStock> findInventarioWhereIdPuntosVenta(Integer id_puntosventa);

	// consulta cuando la cantidad es inferior o igual al stcokMinimo
	@Query(value = "SELECT * "
			+ "FROM cat_stock s, cat_productos pr, cat_puntos_venta pv, cat_categoria cat, cat_disenos di,cat_tallas ta "
			+ "		WHERE  s.id_productos=pr.id_productos AND s.id_puntos_venta=pv.id_puntos_venta AND "
			+ "		pr.id_categoria=cat.id_categoria AND  pr.id_disenos=di.id_disenos AND "
			+ "		pr.id_tallas= ta.id_tallas AND "
			+ "		s.existe='S' AND s.cantidad <= s.stock_min ORDER BY (pr.cod_producto );", nativeQuery = true)
	List<CatStock> findPedidoStockMin();

	// consulta cuando la cantidad es inferior o igual al stcokMinimo solo de un
	// local
	@Query(value = "SELECT * "
			+ "FROM cat_stock s, cat_productos pr, cat_puntos_venta pv, cat_categoria cat, cat_disenos di,cat_tallas ta "
			+ "WHERE  s.id_productos=pr.id_productos AND s.id_puntos_venta=pv.id_puntos_venta AND "
			+ "		pr.id_categoria=cat.id_categoria AND pr.id_disenos=di.id_disenos AND "
			+ "		pr.id_tallas= ta.id_tallas AND  s.existe='S' AND s.cantidad <= s.stock_min AND "
			+ "		pv.id_puntos_venta=? ORDER BY (pr.cod_producto );", nativeQuery = true)
	List<CatStock> findPedidoStockMinWhereIdPuntosVenta(Integer id_puntosventa);

	// consultar producto de acuerdo a diferentes parametros

	@Query(value = "SELECT * "
			+ "FROM cat_stock s, cat_productos pr, cat_puntos_venta pv, cat_categoria cat, cat_disenos di,cat_tallas ta  "
			+ "WHERE  s.id_productos=pr.id_productos AND s.id_puntos_venta=pv.id_puntos_venta AND "
			+ "		pr.id_categoria=cat.id_categoria AND   pr.id_disenos=di.id_disenos AND "
			+ "		pr.id_tallas= ta.id_tallas AND  s.existe='S' AND ( "
			+ "		pr.cod_producto LIKE '%?%'  cat.nombre_categoria  LIKE '%?%' "
			+ "		cat.descripcion  LIKE '%?%'  di.nombre  LIKE '%?%'  ta.medida  LIKE '%?%' "
			+ "		ta.descripcion  LIKE '%?%'  ) ORDER BY (pr.cod_producto );", nativeQuery = true)
	List<CatStock> findProductByAllParameters(String consulta, String consulta1, String consulta2, String consulta3,
			String consulta4, String consulta5);

	// consultar producto de acuerdo a diferentes parametros de acuerdo al local que
	// se encuentre

	@Query(value = "SELECT * "
			+ "FROM cat_stock s, cat_productos pr, cat_puntos_venta pv, cat_categoria cat, cat_disenos di,cat_tallas ta   "
			+ "WHERE  s.id_productos=pr.id_productos AND s.id_puntos_venta=pv.id_puntos_venta AND "
			+ "		pr.id_categoria=cat.id_categoria AND  pr.id_disenos=di.id_disenos AND  "
			+ "		pr.id_tallas= ta.id_tallas AND s.existe='S' AND pv.id_puntos_venta=? AND  (  "
			+ "		pr.cod_producto LIKE '%?%'  or  cat.nombre_categoria  LIKE '%?%'  or "
			+ "		cat.descripcion  LIKE '%?%'  or  di.nombre  LIKE '%?%' or "
			+ "		ta.medida  LIKE '%?%'  or  ta.descripcion  LIKE '%?%'  ) "
			+ "		ORDER BY (pr.cod_producto );", nativeQuery = true)
	List<CatStock> findProductByAllParametersWhereIdPuntosVenta(Integer id_puntosventa, String consulta,
			String consulta1, String consulta2, String consulta3, String consulta4, String consulta5);
	
	
	
}
