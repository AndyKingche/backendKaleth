package com.api.kaleth.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.Clientes;

@RestController
@RequestMapping("/api")
public interface ClientesRepository extends JpaRepository<Clientes, Long>{

	@Query(value="SELECT * FROM ven_cliente WHERE cedula_cli=?", nativeQuery=true)
	List<Clientes> clientesByCedula(String cedula);
}
