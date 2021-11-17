package com.api.kaleth.controller;

import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.api.kaleth.domain.catParametros;
import com.api.kaleth.respository.catParametrosRepository;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class catParametrosController {
	@Autowired
	catParametrosRepository catParametrosRepository;
	
	@GetMapping("/parameters")
	public List<catParametros> getparametro(){
		List<catParametros> parametros = catParametrosRepository.findAll();
		return parametros;
	}
	
	@PutMapping("/parameters/{id}")
	public ResponseEntity<String> updateparamteros(@RequestBody catParametros parametros, @PathVariable Long id)
			throws ResourceNotFoundException{
		
		catParametros catParametros = catParametrosRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No encontramos ninguna talla"));
		
		catParametros.setTextoBanner(parametros.getTextoBanner());
		catParametros.setTelefono(parametros.getTelefono());
		catParametros.setMensajePuntosVenta(parametros.getMensajePuntosVenta());
		
		catParametros.setFraseFooter(parametros.getFraseFooter());
		
		catParametros.setTituloServicios(parametros.getTituloServicios());
		
		catParametros.setServicio1(parametros.getServicio1());
		
		catParametros.setServicio2(parametros.getServicio2());
		
		catParametros.setServicio3(parametros.getServicio3());
		
		catParametros.setServicio4(parametros.getServicio4());
		
		catParametros.setServicio5(parametros.getServicio5());
		
		catParametros.setTituloInformacion(parametros.getTituloInformacion());
		
		catParametros.setCelular(parametros.getCelular());
		
		catParametros.setCorreo1(parametros.getCorreo1());
		
		catParametros.setCorreo2(parametros.getCorreo2());
		
		catParametros.setDireccion(parametros.getDireccion());
		
		catParametros.setUrlFotoBanner1(parametros.getUrlFotoBanner1());
		catParametros.setUrlFotoBanner2(parametros.getUrlFotoBanner2());
		catParametros.setUrlFotoBanner3(parametros.getUrlFotoBanner3());
		
		catParametros.setIdPuntosVentaStock(parametros.getIdPuntosVentaStock());
		catParametros updateParametros = catParametrosRepository.save(catParametros);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La talla se ha actualizado correctamente " + "" + "\"}");
		
	}
}
