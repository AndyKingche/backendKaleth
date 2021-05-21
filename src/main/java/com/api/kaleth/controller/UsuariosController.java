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

import com.api.kaleth.domain.Usuarios;
import com.api.kaleth.respository.UsuariosRepository;

@RestController
@RequestMapping("/api")
public class UsuariosController {
	@Autowired
	UsuariosRepository usuariosrepository;
	
	@GetMapping("/user")
	public List<Usuarios> getUsers(){
		
		List<Usuarios> user = usuariosrepository.findAll();
		
		return user;
				}
	
	@GetMapping("/user/{id}")
	public Optional<Usuarios> getUser(@PathVariable Long id) throws ResourceNotFoundException{
		Optional<Usuarios> user = usuariosrepository.findById(id);
		
		return user;
	}
	
	@PostMapping("/user")
	public Usuarios createUser(@RequestBody Usuarios user) {
		
		return usuariosrepository.save(user);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<String> updateUser(@RequestBody Usuarios user, @PathVariable Long id)
		throws ResourceNotFoundException{
		
		Usuarios findUser = usuariosrepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encuentra el id"));
	
		findUser.setNombre(user.getNombre());
		findUser.setApellido(user.getApellido());
		findUser.setCedula(user.getCedula());
		findUser.setTelefono(user.getTelefono());
		findUser.setEmail(user.getEmail());
		findUser.setPassword(user.getPassword());
		findUser.setEstado(user.getEstado());
		findUser.setFecha_nacimiento(user.getFecha_nacimiento());
		findUser.setDireccion(user.getDireccion());
		findUser.setGenero(user.getGenero());
		
		Usuarios updateUser = usuariosrepository.save(findUser);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Usuario se ha actualizado correctamente " + "" + "\"}");
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteuser(@PathVariable Long id)throws ResourceNotFoundException{
		
		Usuarios findUser = usuariosrepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontro el id"));
		usuariosrepository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Usuario se ha eliminado correctamente " + id + "\"}");
	}
	
}
