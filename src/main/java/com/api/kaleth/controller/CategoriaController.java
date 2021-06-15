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

import com.api.kaleth.domain.CatCategoria;
import com.api.kaleth.respository.CategoriaRepository;

@RestController
@RequestMapping("/api")

public class CategoriaController {
	@Autowired
	CategoriaRepository CatCategoriarespository;
	
	@GetMapping("/category")
	public List<CatCategoria> getCatCategorias(){
		List<CatCategoria> CatCategorias = CatCategoriarespository.findAll();
		
	
		return CatCategorias;
	}
	
	@GetMapping("/category/{id}")
	public Optional<CatCategoria> getCatCategoria(@PathVariable Long id)throws ResourceNotFoundException{
		
		Optional<CatCategoria> CatCategoria = CatCategoriarespository.findById(id);
		return CatCategoria;
				
	}
	
	@PostMapping("/category")
	public CatCategoria createCatCategoria(@RequestBody CatCategoria CatCategoria) {
		CatCategoria newCatCategoria = CatCategoriarespository.save(CatCategoria);
		
		return newCatCategoria;
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<String> updateCatCategoria(@RequestBody CatCategoria CatCategoria, @PathVariable Long id)
	throws ResourceNotFoundException{
		CatCategoria findCatCategoria = CatCategoriarespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No encontramos ningun id"));
		
		findCatCategoria.setNombreCategoria(CatCategoria.getNombreCategoria());
		findCatCategoria.setDescripcion(CatCategoria.getDescripcion());
		
		CatCategoria updateCatCategoria = CatCategoriarespository.save(findCatCategoria);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La CatCategoria se ha actualizado correctamente " + id + "\"}");
	}
	
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<String> deleteCatCategoria(@PathVariable Long id){
		
		CatCategoriarespository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
		.body("{\"mensaje\": \"La CatCategoria se ha eliminado correctamente " + id + "\"}");
	}
	
}
