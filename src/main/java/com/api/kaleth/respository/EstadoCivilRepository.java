package com.api.kaleth.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.api.kaleth.domain.UsEstadocivil;

@RestController
@RequestMapping("/api")
public interface EstadoCivilRepository extends JpaRepository<UsEstadocivil, Long>{

}
