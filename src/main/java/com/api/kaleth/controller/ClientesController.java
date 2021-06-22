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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.Clientes;
import com.api.kaleth.respository.ClientesRepository;

@RestController
@RequestMapping("/api")
public class ClientesController {

	@Autowired
	ClientesRepository clientesRepository;
	
	@GetMapping("/client")
	public List<Clientes> getClientes(){
		List<Clientes> Clientess = clientesRepository.findAll();
		
	
		return Clientess;
	}
	
	@GetMapping("/client/{id}")
	public Optional<Clientes> getCliente(@PathVariable Long id)throws ResourceNotFoundException{
		
		Optional<Clientes> Clientes = clientesRepository.findById(id);
		return Clientes;
				
	}
	
	@PostMapping("/client")
	public Clientes createClientes(@RequestBody Clientes Clientes) {
		Clientes newClientes = clientesRepository.save(Clientes);
		
		return newClientes;
	}
	
	@PutMapping("/client/{id}")
	public ResponseEntity<String> updateClientes(@RequestBody Clientes Clientes, @PathVariable Long id)
	throws ResourceNotFoundException{
		Clientes findClientes = clientesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No encontramos ningun id"));
		
		findClientes.setNombrecliente(Clientes.getNombrecliente());
		findClientes.setApellidocliente(Clientes.getApellidocliente());
		findClientes.setCedulacliente(Clientes.getCedulacliente());
		findClientes.setDireccioncliente(Clientes.getDireccioncliente());
		findClientes.setEmail(Clientes.getEmail());
		findClientes.setTelefono(Clientes.getTelefono());
		
		Clientes updateClientes = clientesRepository.save(findClientes);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La Clientes se ha actualizado correctamente " + id + "\"}");
	}
	
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<String> deleteClientes(@PathVariable Long id){
		
		clientesRepository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
		.body("{\"mensaje\": \"La Clientes se ha eliminado correctamente " + id + "\"}");
	}
	
	@RequestMapping(value="/client/findcedula/{cedula}", produces = {"application/json"},method= RequestMethod.GET)
	public List<Clientes> findbyCedula(@PathVariable("cedula") String cedula){
		
		try {
			List<Clientes> clienteencontrado = clientesRepository.clientesByCedula(cedula);
			return clienteencontrado;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
