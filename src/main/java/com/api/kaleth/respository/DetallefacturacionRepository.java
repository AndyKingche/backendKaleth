package com.api.kaleth.respository;

import com.api.kaleth.domain.Detallefacturacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public interface DetallefacturacionRepository extends JpaRepository<Detallefacturacion,Long> {
    
}
