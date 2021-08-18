package com.api.kaleth.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.PeCabezaPedido;

@RestController
@RequestMapping("/api")
public interface CabezaPedidoRepository extends JpaRepository<PeCabezaPedido, Long>{
	@Query(value = "DELETE FROM pe_detalle_pedido WHERE id_cabeza_fac=?", nativeQuery = true)
	int deleteDetallePedido(Integer idCabezaPe);
}
