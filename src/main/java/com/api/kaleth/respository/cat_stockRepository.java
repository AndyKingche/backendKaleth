package com.api.kaleth.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.CatStock;


@RestController
@RequestMapping("/api")
public interface cat_stockRepository extends JpaRepository<CatStock, Long>{

	@Query(value="SELECT * FROM cat_stock WHERE id_producto=? and id_puntoventas=?", nativeQuery = true)
	int encontrarStock(Integer id_producto,Integer id_puntoventa);
	
}
