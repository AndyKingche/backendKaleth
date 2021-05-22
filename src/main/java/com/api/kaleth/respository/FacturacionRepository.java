package com.api.kaleth.respository;

import com.api.kaleth.domain.Facturacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public interface FacturacionRepository extends JpaRepository<Facturacion,Long>{
    
}
