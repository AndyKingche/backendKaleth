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

import com.api.kaleth.domain.Rolusuarios;
import com.api.kaleth.respository.RolusuariosRepository;

@RestController
@RequestMapping("/api")
public class RolusuariosController{
	@Autowired
	RolusuariosRepository roluserrepository;
	
	@GetMapping("/rol-user")
	public List<Rolusuarios> getRolUsers(){
		
		List<Rolusuarios> rolusers= roluserrepository.findAll();
		
		return rolusers;
	}
	
	@GetMapping("/rol-user/{id}")
	public Optional<Rolusuarios> getRoluser(@PathVariable Long id)throws ResourceNotFoundException{
		Optional<Rolusuarios> roluser=roluserrepository.findById(id);
		return roluser;
		
	}
	
	@PostMapping("/rol-user")
	public Rolusuarios createRolUser(@RequestBody Rolusuarios roluser) {
		Rolusuarios newRoluser = roluserrepository.save(roluser);
		return newRoluser;
	}
	
	@PutMapping("/rol-user/{id}")
	public ResponseEntity<String> updateRoluser(@RequestBody Rolusuarios roluser,@PathVariable Long id)
	throws ResourceNotFoundException{
		Rolusuarios findRoluser = roluserrepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No se encontro ningun id"));
				
		findRoluser.setRol(roluser.getRol());
		findRoluser.setUser(roluser.getUser());
		
		Rolusuarios updateRoluser = roluserrepository.save(findRoluser);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El rol-usuario se ha actualizado correctamente " + "" + "\"}");
	}
	
	@DeleteMapping("/rol-user/{id}")
	public ResponseEntity<String> deleteRoluser(@PathVariable Long id)throws ResourceNotFoundException{
		
		roluserrepository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El rol-usuario se ha elimidao correctamente " + "" + "\"}");
		
	}

}
