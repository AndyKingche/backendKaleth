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

import com.api.kaleth.domain.Categoria;
import com.api.kaleth.respository.CategoriaRepository;

@RestController
@RequestMapping("/api")

public class CategoriaController {
	@Autowired
	CategoriaRepository categoriarespository;
	
	@GetMapping("/category")
	public List<Categoria> getCategorias(){
		List<Categoria> categorias = categoriarespository.findAll();
		System.out.println("Hola");
	
		return categorias;
	}
	
	@GetMapping("/category/{id}")
	public Optional<Categoria> getCategoria(@PathVariable Long id)throws ResourceNotFoundException{
		
		Optional<Categoria> categoria = categoriarespository.findById(id);
		return categoria;
				
	}
	
	@PostMapping("/category")
	public Categoria createCategoria(@RequestBody Categoria categoria) {
		Categoria newCategoria = categoriarespository.save(categoria);
		
		return newCategoria;
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<String> updateCategoria(@RequestBody Categoria categoria, @PathVariable Long id)
	throws ResourceNotFoundException{
		Categoria findcategoria = categoriarespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No encontramos ningun id"));
		
		findcategoria.setNombre(categoria.getNombre());
		findcategoria.setDescripcion(categoria.getDescripcion());
		
		Categoria updatecategoria = categoriarespository.save(findcategoria);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La categoria se ha actualizado correctamente " + id + "\"}");
	}
	
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<String> deleteCategoria(@PathVariable Long id){
		
		categoriarespository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
		.body("{\"mensaje\": \"La categoria se ha eliminado correctamente " + id + "\"}");
	}
	

	



}
