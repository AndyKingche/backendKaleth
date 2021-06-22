package com.api.kaleth.respository;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.CatTalla;


@RestController
@RequestMapping("/api")
public interface CatTallasRepository extends JpaRepository<CatTalla, Long>{

	@Query(value="SELECT id_tallas FROM cat_tallas WHERE medida=?", nativeQuery = true)
	int encontrarTalla(String medida);
}
