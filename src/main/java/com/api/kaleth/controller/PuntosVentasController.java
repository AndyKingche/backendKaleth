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


import com.api.kaleth.domain.CatPuntosVenta;
import com.api.kaleth.respository.PuntosVentasRepository;

@RestController
@RequestMapping("/api")
public class PuntosVentasController {

	@Autowired
	PuntosVentasRepository CatPuntosVentarepository;
	
	@GetMapping("/sales-points")
	public List<CatPuntosVenta> getCatPuntosVenta(){
		List<CatPuntosVenta> CatPuntosVenta = CatPuntosVentarepository.findAll();
		return CatPuntosVenta;
		
		
		
	}
	
	@GetMapping("/sales-points/{id}")
	public Optional<CatPuntosVenta> getCatPuntosVenta(@PathVariable int id) 
			throws ResourceNotFoundException{
		Optional<CatPuntosVenta> CatPuntosVenta = CatPuntosVentarepository.findById(id);
		return CatPuntosVenta;
	}
	
	@PostMapping("/sales-points")
	public CatPuntosVenta createCatPuntosVenta(@RequestBody CatPuntosVenta CatPuntosVenta) {
		
		CatPuntosVenta newCatPuntosVenta = CatPuntosVentarepository.save(CatPuntosVenta);
		return newCatPuntosVenta;
	}
	
	@PutMapping("/sales-points/{id}")
	public ResponseEntity<String> updateCatPuntosVenta(@RequestBody CatPuntosVenta CatPuntosVenta, @PathVariable int id)
	throws ResourceNotFoundException{
		
		CatPuntosVenta findCatPuntosVenta = CatPuntosVentarepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No encuentro ningun disenos que pidio"));
		findCatPuntosVenta.setNombreLocal(CatPuntosVenta.getNombreLocal());
		findCatPuntosVenta.setCiudad(CatPuntosVenta.getCiudad());
		findCatPuntosVenta.setDireccion(CatPuntosVenta.getDireccion());
		findCatPuntosVenta.setTelefono(CatPuntosVenta.getTelefono());
		
		CatPuntosVenta updateCatPuntosVenta = CatPuntosVentarepository.save(findCatPuntosVenta);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El diseno se ha actualizado correctamente " + id + "\"}");
		
	}
	
	@DeleteMapping("/sales-points/{id}")
	public ResponseEntity<String> deleteCatPuntosVenta(@PathVariable int id)
	throws ResourceNotFoundException{
		
		CatPuntosVentarepository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El diseno se ha eliminado correctamente " + id + "\"}");
		
	}
	
}
