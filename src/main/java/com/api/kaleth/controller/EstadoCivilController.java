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

import com.api.kaleth.domain.EstadoCivil;
import com.api.kaleth.respository.EstadoCivilRepository;

@RestController
@RequestMapping("/api")
public class EstadoCivilController {
	@Autowired
	EstadoCivilRepository estadocivilrepository;
	
	@GetMapping("/civil-status")
	public List<EstadoCivil> getEstadoCiviles(){
		List<EstadoCivil> estadosciviles = estadocivilrepository.findAll();
		
		return estadosciviles;
	}
	
	@GetMapping("/civil-status/{id}")
	public Optional<EstadoCivil> getEstadoCivil(@PathVariable Long id) {
		Optional<EstadoCivil> estadocivil = estadocivilrepository.findById(id);
		return estadocivil;
	}
	
	@PostMapping("/civil-status")
	public EstadoCivil createEcivil(@RequestBody EstadoCivil estadocivil) {
		EstadoCivil newestado = estadocivilrepository.save(estadocivil);
		return newestado;
	}
	
	@PutMapping("/civil-status/{id}")
	public ResponseEntity<String> updateECivil(@RequestBody EstadoCivil estadocivil,@PathVariable Long id) 
	throws ResourceNotFoundException{
		
		EstadoCivil findestado = estadocivilrepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontro ningun id"));
		findestado.setNombre(estadocivil.getNombre());
		
		EstadoCivil actualizarestado = estadocivilrepository.save(findestado);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El estado se ha actualizado correctamente " + id + "\"}");
		
	}
	
	@DeleteMapping("/civil-status/{id}")
	public ResponseEntity<String> deleteECivil(@PathVariable Long id) 
			throws ResourceNotFoundException{
		
		estadocivilrepository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El estado se ha eliminado correctamente " + id + "\"}");
		}
	
}
