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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.CatProducto;
import com.api.kaleth.domain.CatStock;
import com.api.kaleth.respository.cat_stockRepository;

@RestController
@RequestMapping("/api")
public class cat_stockController {
	@Autowired
	cat_stockRepository cat_stockRepository;


	@GetMapping("/stock")
	public List<CatStock> getCatDiseno(){
		List<CatStock> CatStock = cat_stockRepository.findAll();
		return CatStock;
		
	}
	
	@GetMapping("/stock/{id}")
	public Optional<CatStock> getStock(@PathVariable Long id) throws ResourceNotFoundException{
		Optional<CatStock> OneStock = cat_stockRepository.findById(id);
		
		return OneStock;
	}
	@PostMapping("/stock")
	public CatStock createCatStock(@RequestBody CatStock CatStock) {

		CatStock newStock = cat_stockRepository.save(CatStock);

		return newStock;
	}
	
	@RequestMapping(value="/stock/number/{id_producto}/{id_puntoventa}",produces = {"application/json"},method= RequestMethod.GET)
	public int encontrarStock(@PathVariable("id_producto") Integer id_producto, @PathVariable("id_puntoventa") Integer id_puntoventa) {
		int numeroTipo= cat_stockRepository.encontrarStock(id_producto, id_puntoventa);
		
		return numeroTipo;
	}


	
	@PutMapping("/stock/{id}")
	public ResponseEntity<String> updateStock(@RequestBody CatStock stock, @PathVariable Long id)
		throws ResourceNotFoundException{
		
		CatStock findStock = cat_stockRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encuentra el id"));
	
		findStock.setCantidad(stock.getCantidad());
		findStock.setStockMax(stock.getStockMax());
		findStock.setStockMin(stock.getStockMin());
		findStock.setPrecioDistribuidor(stock.getPrecioDistribuidor());
		findStock.setPrecioMayor(stock.getPrecioMayor());
		findStock.setPrecioUnit(stock.getPrecioUnit());
		findStock.setExiste(stock.getExiste());
		
		CatStock updateStock = cat_stockRepository.save(findStock);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Stock se ha actualizado correctamente " + "" + "\"}");
	}
	@DeleteMapping("/stock/{id}")
	public ResponseEntity<String> deleteStock(@PathVariable Long id)throws ResourceNotFoundException{
		
		CatStock findStock = cat_stockRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontro el id"));
		cat_stockRepository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Stock se ha eliminado correctamente " + id + "\"}");
	}
}
