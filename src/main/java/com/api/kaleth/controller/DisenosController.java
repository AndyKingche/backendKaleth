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

import com.api.kaleth.domain.Disenos;
import com.api.kaleth.respository.DisenosRepository;



@RestController
@RequestMapping("/api")
public class DisenosController {
	@Autowired
	DisenosRepository disenosrespository;
	
	@GetMapping("/desing")
	public List<Disenos> getDisenos(){
		List<Disenos> disenos = disenosrespository.findAll();
		return disenos;
		
	}
	
	@GetMapping("/desing/{id}")
	public Optional<Disenos> getDiseno(@PathVariable Long id) 
			throws ResourceNotFoundException{
		Optional<Disenos> diseno = disenosrespository.findById(id);
		return diseno;
	}
	
	@PostMapping("/desing")
	public Disenos createDiseno(@RequestBody Disenos disenos) {
		
		Disenos newdisenos = disenosrespository.save(disenos);
		return newdisenos;
	}
	
	@PutMapping("/desing/{id}")
	public ResponseEntity<String> updateDisenos(@RequestBody Disenos disenos, @PathVariable Long id)
	throws ResourceNotFoundException{
		
		Disenos findDisenos = disenosrespository.findById(id).orElseThrow(()->new ResourceNotFoundException("No encuentro ningun disenos que pidio"));
		findDisenos.setNombre(disenos.getNombre());
		
		Disenos updateDisenos = disenosrespository.save(findDisenos);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El diseno se ha actualizado correctamente " + id + "\"}");
		
	}
	
	@DeleteMapping("/desing/{id}")
	public ResponseEntity<String> deleteDisenos(@PathVariable Long id)
	throws ResourceNotFoundException{
		
		disenosrespository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El diseno se ha eliminado correctamente " + id + "\"}");
		
	}
}
