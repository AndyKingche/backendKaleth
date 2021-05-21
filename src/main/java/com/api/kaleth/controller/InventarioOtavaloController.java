package com.api.kaleth.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.InventarioOtavalo;
import com.api.kaleth.respository.InventarioOtavaloRepository;

@RestController
@RequestMapping("/api")
public class InventarioOtavaloController {
	@Autowired
	InventarioOtavaloRepository inventariorepository;
	
	@GetMapping("/inventary-otv")
	public List<InventarioOtavalo> getInventarios(){
		List<InventarioOtavalo> inventarios = inventariorepository.findAll();
		
		return inventarios;
	}
	
	@GetMapping("/inventary-otv/{id}")
	public Optional<InventarioOtavalo> getInventario(@PathVariable Long id)throws ResourceNotFoundException{
		Optional<InventarioOtavalo> inventario = inventariorepository.findById(id);
		return inventario;
	}
	
	@PostMapping("/inventary-otv")
	public InventarioOtavalo createInventario(@RequestBody InventarioOtavalo inventario) {
		InventarioOtavalo newinventario = inventariorepository.save(inventario);
		return newinventario;
	}
	
	@PutMapping("/inventary-otv/{id}")
	public ResponseEntity<String> updateInventario(@RequestBody InventarioOtavalo inventario,@PathVariable Long id)
	throws ResourceNotFoundException {
		
		InventarioOtavalo findinventario=inventariorepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No hay ningun producto con ese id"));
		findinventario.setPrecio_distribuidor(inventario.getPrecio_distribuidor());
		findinventario.setPrecio_mayor(inventario.getPrecio_mayor());
		findinventario.setPrecio_unitario(inventario.getPrecio_unitario());
		findinventario.setStock_min(inventario.getStock_min());
		findinventario.setStock_total(inventario.getStock_total());
		findinventario.setProductos(inventario.getProductos());
		
		InventarioOtavalo updateinventario = inventariorepository.save(findinventario);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El inventario-otavalo se ha actualizado correctamente " + id + "\"}");
		
	}
	
	@DeleteMapping("/inventary-otv/{id}")
	public ResponseEntity<String> deleteInventario(@PathVariable Long id)
	{
		inventariorepository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El inventario-otavalo se ha eliminado correctamente " + id + "\"}");
	}
}
