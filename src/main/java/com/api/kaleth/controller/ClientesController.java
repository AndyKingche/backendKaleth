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

import com.api.kaleth.domain.VenCliente;
import com.api.kaleth.respository.ClientesRepository;

@RestController
@RequestMapping("/api")
public class ClientesController {

	@Autowired
	ClientesRepository VenClienteRepository;
	
	@GetMapping("/client")
	public List<VenCliente> getVenCliente(){
		List<VenCliente> VenClientes = VenClienteRepository.findAll();
		
	
		return VenClientes;
	}
	
	@GetMapping("/client/{id}")
	public Optional<VenCliente> getCliente(@PathVariable Long id)throws ResourceNotFoundException{
		
		Optional<VenCliente> VenCliente = VenClienteRepository.findById(id);
		return VenCliente;
				
	}
	
	@PostMapping("/client")
	public VenCliente createVenCliente(@RequestBody VenCliente VenCliente) {
		VenCliente newVenCliente = VenClienteRepository.save(VenCliente);
		
		return newVenCliente;
	}
	
	@PutMapping("/client/{id}")
	public ResponseEntity<String> updateVenCliente(@RequestBody VenCliente VenCliente, @PathVariable Long id)
	throws ResourceNotFoundException{
		VenCliente findVenCliente = VenClienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No encontramos ningun id"));
		
		findVenCliente.setNombreCli(VenCliente.getNombreCli());
		findVenCliente.setApellidoCli(VenCliente.getApellidoCli());
		findVenCliente.setCedulaCli(VenCliente.getCedulaCli());
		findVenCliente.setDireccionCli(VenCliente.getDireccionCli());
		findVenCliente.setEmail(VenCliente.getEmail());
		findVenCliente.setTelefono(VenCliente.getTelefono());
		
		VenCliente updateVenCliente = VenClienteRepository.save(findVenCliente);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La VenCliente se ha actualizado correctamente " + id + "\"}");
	}
	
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<String> deleteVenCliente(@PathVariable Long id){
		
		VenClienteRepository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
		.body("{\"mensaje\": \"La VenCliente se ha eliminado correctamente " + id + "\"}");
	}
	
	@RequestMapping(value="/client/findcedula/{cedula}", produces = {"application/json"},method= RequestMethod.GET)
	public List<VenCliente> findbyCedula(@PathVariable("cedula") String cedula){
		
		try {
			List<VenCliente> clienteencontrado = VenClienteRepository.clientesByCedula(cedula);
			return clienteencontrado;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
