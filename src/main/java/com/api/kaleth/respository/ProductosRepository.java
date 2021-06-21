package com.api.kaleth.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.CatProducto;


@RestController
@RequestMapping("/api")
public interface ProductosRepository extends JpaRepository<CatProducto, Long>{

	@Query(value = "SELECT id_productos FROM cat_productos p WHERE p.cod_producto=?", nativeQuery = true)
	int findBycodigo(Integer codigo);
	

}
