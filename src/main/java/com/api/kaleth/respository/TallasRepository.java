package com.api.kaleth.respository;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.CatTalla;


@RestController
@RequestMapping("/api")
public interface TallasRepository extends JpaRepository<CatTalla, Long>{

}
