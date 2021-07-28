package com.api.kaleth.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.UsEstadocivil;
import com.api.kaleth.respository.EstadoCivilRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class EstadoCivilController {
	@Autowired
	EstadoCivilRepository UsEstadocivilrepository;
	
	@GetMapping("/civil-status")
	public List<UsEstadocivil> getUsEstadociviles(){
		List<UsEstadocivil> estadosciviles = UsEstadocivilrepository.findAll();
		
		return estadosciviles;
	}
	
	@GetMapping("/civil-status/{id}")
	public Optional<UsEstadocivil> getUsEstadocivil(@PathVariable Long id) {
		Optional<UsEstadocivil> UsEstadocivil = UsEstadocivilrepository.findById(id);
		return UsEstadocivil;
	}
	
	@PostMapping("/civil-status")
	public UsEstadocivil createEcivil(@RequestBody UsEstadocivil UsEstadocivil) {
		UsEstadocivil newestado = UsEstadocivilrepository.save(UsEstadocivil);
		return newestado;
	}
	
	@PutMapping("/civil-status/{id}")
	public ResponseEntity<String> updateECivil(@RequestBody UsEstadocivil UsEstadocivil,@PathVariable Long id) 
	throws ResourceNotFoundException{
		
		UsEstadocivil findestado = UsEstadocivilrepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontro ningun id"));
		findestado.setNombre(UsEstadocivil.getNombre());
		System.out.println(findestado);
		UsEstadocivil actualizarestado = UsEstadocivilrepository.save(findestado);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El estado se ha actualizado correctamente " + id + "\"}");
		
	}
	
	@DeleteMapping("/civil-status/{id}")
	public ResponseEntity<String> deleteECivil(@PathVariable Long id) 
			throws ResourceNotFoundException{
		
		UsEstadocivilrepository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El estado se ha eliminado correctamente " + id + "\"}");
		}
	
}
