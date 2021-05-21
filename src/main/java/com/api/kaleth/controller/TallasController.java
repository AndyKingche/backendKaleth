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

import com.api.kaleth.domain.Tallas;
import com.api.kaleth.respository.TallasRepository;

@RestController
@RequestMapping("/api")
public class TallasController {
	@Autowired
	TallasRepository tallasrepository;
	
	@GetMapping("/size")
	public List<Tallas> getTallas(){
		List<Tallas> tallas = tallasrepository.findAll();
		return tallas;
	}
	
	@GetMapping("/size/{id}")
	public Optional<Tallas> getTalla(@PathVariable Long id)throws ResourceNotFoundException{
		
		Optional<Tallas> talla = tallasrepository.findById(id);
		return talla;
	}
	
	@PostMapping("/size")
	public Tallas createTallas(@RequestBody Tallas talla) {
		
		Tallas newtalla = tallasrepository.save(talla);
		return newtalla;
	}
	
	@PutMapping("/size/{id}")
	public ResponseEntity<String> updateTallas(@RequestBody Tallas talla, @PathVariable Long id)
			throws ResourceNotFoundException{
		
		Tallas findTalla = tallasrepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No encontramos ninguna talla"));
		findTalla.setNombre(talla.getNombre());
		findTalla.setDescripcion(talla.getDescripcion());
		
		Tallas updateTalla = tallasrepository.save(findTalla);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La talla se ha actualizado correctamente " + "" + "\"}");
		
	}
	
	@DeleteMapping("/size/{id}")
	public ResponseEntity<String> deleteTallas(@PathVariable Long id)throws ResourceNotFoundException{
		tallasrepository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La talla se ha eliminado correctamente " + "" + "\"}");
		
	}
}
