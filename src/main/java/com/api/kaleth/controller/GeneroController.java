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

import com.api.kaleth.domain.Genero;
import com.api.kaleth.respository.GeneroRepository;

@RestController
@RequestMapping("/api")
public class GeneroController {
	@Autowired
	GeneroRepository generorespository;
	
	@GetMapping("/gender")
	public List<Genero> getGenders(){
		List<Genero> genders=generorespository.findAll();
		return genders;
	}
	
	@GetMapping("/gender/{id}")
	public Optional<Genero> getGender(@PathVariable Long id)throws ResourceNotFoundException{
		Optional<Genero> gender = generorespository.findById(id);
		return gender;
	}
	
	@PostMapping("/gender")
	public Genero createGender(@RequestBody Genero genero){
		return generorespository.save(genero);
	}
	
	@PutMapping("/gender/{id}")
	public ResponseEntity<String> updateGender(@RequestBody Genero genero,@PathVariable Long id)
	throws ResourceNotFoundException{
		Genero findgender = generorespository.findById(id).orElseThrow(()-> new ResourceNotFoundException());
		findgender.setNombre(genero.getNombre());
		
		Genero actualizargenero = generorespository.save(findgender);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El genero se ha actualizado correctamente " + id + "\"}");
		
	}
	
	@DeleteMapping("/gender/{id}")
	public ResponseEntity<String> deleteGender(@PathVariable Long id)throws ResourceNotFoundException{
		generorespository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El genero se ha eliminado correctamente " + id + "\"}");
	}
}
