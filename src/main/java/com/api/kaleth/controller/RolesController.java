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

import com.api.kaleth.domain.UsRole;
import com.api.kaleth.respository.RolesRepository;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class RolesController {
	@Autowired
	RolesRepository UsRolerespository;
	
	@GetMapping("/roles")
	public List<UsRole> getUsRole(){
		List<UsRole> UsRole = UsRolerespository.findAll();
		return UsRole;
	}
	
	@GetMapping("/roles/{id}")
	public Optional<UsRole> getRol(@PathVariable Long id) throws ResourceNotFoundException{
		Optional<UsRole> rol = UsRolerespository.findById(id);
		return rol;
	}
	
	@PostMapping("/roles")
	public UsRole createRole(@RequestBody UsRole rol) {
		return UsRolerespository.save(rol);
	}
	
	@PutMapping("/roles/{id}")
	public ResponseEntity<String> updateRol(@RequestBody UsRole rol, @PathVariable Long id)throws ResourceNotFoundException{
		UsRole findRol = getRol(id).orElseThrow(()-> new ResourceNotFoundException("No se encontro el id"));
		findRol.setNombre(rol.getNombre());
		findRol.setRol(rol.getRol());
		findRol.setDescripcion(rol.getDescripcion());
		
		UsRole actualizarRol = UsRolerespository.save(findRol);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Rol se ha actualizado correctamente " + "" + "\"}");
	
	}
	
	@DeleteMapping("/roles/{id}")
	public ResponseEntity<String> delteRol(@PathVariable Long id)throws ResourceNotFoundException{
		
		UsRolerespository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Rol se ha eliminado correctamente " + id + "\"}");
		
	}
}
