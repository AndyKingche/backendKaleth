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

import com.api.kaleth.domain.Roles;
import com.api.kaleth.respository.RolesRepository;


@RestController
@RequestMapping("/api")
public class RolesController {
	@Autowired
	RolesRepository rolesrespository;
	
	@GetMapping("/roles")
	public List<Roles> getRoles(){
		List<Roles> roles = rolesrespository.findAll();
		return roles;
	}
	
	@GetMapping("/roles/{id}")
	public Optional<Roles> getRol(@PathVariable Long id) throws ResourceNotFoundException{
		Optional<Roles> rol = rolesrespository.findById(id);
		return rol;
	}
	
	@PostMapping("/roles")
	public Roles createRole(@RequestBody Roles rol) {
		return rolesrespository.save(rol);
	}
	
	@PutMapping("/roles/{id}")
	public ResponseEntity<String> updateRol(@RequestBody Roles rol, @PathVariable Long id)throws ResourceNotFoundException{
		Roles findRol = getRol(id).orElseThrow(()-> new ResourceNotFoundException("No se encontro el id"));
		findRol.setNombre(rol.getNombre());
		findRol.setRol(rol.getRol());
		findRol.setDescripcion(rol.getDescripcion());
		
		Roles actualizarRol = rolesrespository.save(findRol);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Rol se ha actualizado correctamente " + "" + "\"}");
	
	}
	
	@DeleteMapping("/roles/{id}")
	public ResponseEntity<String> delteRol(@PathVariable Long id)throws ResourceNotFoundException{
		
		rolesrespository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Rol se ha eliminado correctamente " + id + "\"}");
		
	}
}
