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

import com.api.kaleth.domain.UsGenero;
import com.api.kaleth.respository.GeneroRepository;

@RestController
@RequestMapping("/api")
public class GeneroController {
	@Autowired
	GeneroRepository UsGenerorespository;
	
	@GetMapping("/gender")
	public List<UsGenero> getGenders(){
		List<UsGenero> genders=UsGenerorespository.findAll();
		return genders;
	}
	
	@GetMapping("/gender/{id}")
	public Optional<UsGenero> getGender(@PathVariable Long id)throws ResourceNotFoundException{
		Optional<UsGenero> gender = UsGenerorespository.findById(id);
		return gender;
	}
	
	@PostMapping("/gender")
	public UsGenero createGender(@RequestBody UsGenero UsGenero){
		return UsGenerorespository.save(UsGenero);
	}
	
	@PutMapping("/gender/{id}")
	public ResponseEntity<String> updateGender(@RequestBody UsGenero UsGenero,@PathVariable Long id)
	throws ResourceNotFoundException{
		UsGenero findgender = UsGenerorespository.findById(id).orElseThrow(()-> new ResourceNotFoundException());
		findgender.setNombre(UsGenero.getNombre());
		
		UsGenero actualizarUsGenero = UsGenerorespository.save(findgender);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El UsGenero se ha actualizado correctamente " + id + "\"}");
		
	}
	
	@DeleteMapping("/gender/{id}")
	public ResponseEntity<String> deleteGender(@PathVariable Long id)throws ResourceNotFoundException{
		UsGenerorespository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El UsGenero se ha eliminado correctamente " + id + "\"}");
	}
}
