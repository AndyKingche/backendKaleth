package com.api.kaleth.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.CatCategoria;


@RestController
@RequestMapping("/api")
public interface CategoriaRepository extends JpaRepository<CatCategoria, Long> {
	@Query(value="SELECT id_categoria FROM cat_categoria WHERE nombre_categoria=?", nativeQuery = true)
	int encontrarCategoria(String nombre);
}
