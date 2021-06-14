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

import com.api.kaleth.respository.cat_stockRepository;

@RestController
@RequestMapping("/api")
public class cat_stockController {
//	@Autowired
//	cat_stockRepository cat_stockRepository;


	//@GetMapping("/stock/{id}")
	//public Optional<cat_stock> getStock(@PathVariable Long id) throws ResourceNotFoundException{
		//Optional<cat_stock> OneStock = cat_stockRepository.findById(id);
		
		//return OneStock;
	//}

	
//	@PutMapping("/stock/{id}")
//	public ResponseEntity<String> updateStock(@RequestBody cat_stock stock, @PathVariable Long id)
//		throws ResourceNotFoundException{
//		
//		cat_stock findStock = cat_stockRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encuentra el id"));
//	
//		findStock.setCantidad(stock.getCantidad());
//		findStock.setStock_max(stock.getStock_max());
//		findStock.setStock_min(stock.getStock_min());
//		findStock.setPrecio_distribuidor(stock.getPrecio_distribuidor());
//		findStock.setPrecio_mayor(stock.getPrecio_mayor());
//		findStock.setPrecio_unit(stock.getPrecio_unit());
//		findStock.setExiste(stock.getExiste());
//		
//		cat_stock updateStock = cat_stockRepository.save(findStock);
//		return ResponseEntity.ok().header("Content-Type", "application/json")
//				.body("{\"mensaje\": \"El Stock se ha actualizado correctamente " + "" + "\"}");
//	}
//	@DeleteMapping("/stock/{id}")
//	public ResponseEntity<String> deleteStock(@PathVariable Long id)throws ResourceNotFoundException{
//		
//		cat_stock findStock = cat_stockRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontro el id"));
//		cat_stockRepository.deleteById(id);
//		
//		return ResponseEntity.ok().header("Content-Type", "application/json")
//				.body("{\"mensaje\": \"El Stock se ha eliminado correctamente " + id + "\"}");
//	}
}
