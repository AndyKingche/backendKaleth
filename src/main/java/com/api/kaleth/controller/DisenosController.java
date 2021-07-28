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

import com.api.kaleth.domain.CatDiseno;
import com.api.kaleth.respository.DisenosRepository;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class DisenosController {
	@Autowired
	DisenosRepository CatDisenorespository;
	
	@GetMapping("/desing")
	public List<CatDiseno> getCatDiseno(){
		List<CatDiseno> CatDiseno = CatDisenorespository.findAll();
		return CatDiseno;
		
	}
	
	@GetMapping("/desing/{id}")
	public Optional<CatDiseno> getDiseno(@PathVariable Long id) 
			throws ResourceNotFoundException{
		Optional<CatDiseno> diseno = CatDisenorespository.findById(id);
		return diseno;
	}
	
	@PostMapping("/desing")
	public CatDiseno createDiseno(@RequestBody CatDiseno CatDiseno) {
		
		CatDiseno newCatDiseno = CatDisenorespository.save(CatDiseno);
		return newCatDiseno;
	}
	
	@PutMapping("/desing/{id}")
	public ResponseEntity<String> updateCatDiseno(@RequestBody CatDiseno CatDiseno, @PathVariable Long id)
	throws ResourceNotFoundException{
		
		CatDiseno findCatDiseno = CatDisenorespository.findById(id).orElseThrow(()->new ResourceNotFoundException("No encuentro ningun CatDiseno que pidio"));
		findCatDiseno.setNombre(CatDiseno.getNombre());
		findCatDiseno.setUrlFoto(CatDiseno.getUrlFoto());
		findCatDiseno.setUrlFoto1(CatDiseno.getUrlFoto1());
		findCatDiseno.setUrlFoto2(CatDiseno.getUrlFoto2());
		CatDiseno updateCatDiseno = CatDisenorespository.save(findCatDiseno);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El diseno se ha actualizado correctamente " + id + "\"}");
		
	}
	
	@DeleteMapping("/desing/{id}")
	public ResponseEntity<String> deleteCatDiseno(@PathVariable Long id)
	throws ResourceNotFoundException{
		
		CatDisenorespository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El diseno se ha eliminado correctamente " + id + "\"}");
		
	}
	
	@RequestMapping(value="/desing/find/{nombre}",produces = {"application/json"},method= RequestMethod.GET)
	public int findproductobymedida(@PathVariable("nombre") String nombre) {
		int idencontrado;	
		try {
			idencontrado = CatDisenorespository.encontrarDiseno(nombre);
			 System.out.println(idencontrado);
			 
			 return idencontrado;
		} catch (Exception e) {
			System.out.println("*************************ERRRORRRRRRRRRRRRRRRRRRR");
		}
		return 0;
	}

}
