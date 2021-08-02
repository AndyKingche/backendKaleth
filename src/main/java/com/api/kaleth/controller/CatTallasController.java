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

import com.api.kaleth.domain.CatProducto;
import com.api.kaleth.domain.CatTalla;
import com.api.kaleth.respository.CatTallasRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CatTallasController {
	@Autowired
	CatTallasRepository CatTallarepository;
	
	@GetMapping("/size")
	public List<CatTalla> getCatTalla(){
		List<CatTalla> CatTalla = CatTallarepository.findAll();
		return CatTalla;
	}
	
	@GetMapping("/size/{id}")
	public Optional<CatTalla> getTalla(@PathVariable Long id)throws ResourceNotFoundException{
		
		Optional<CatTalla> talla = CatTallarepository.findById(id);
		return talla;
	}
	
	@PostMapping("/size")
	public CatTalla createCatTalla(@RequestBody CatTalla talla) {
		
		CatTalla newtalla = CatTallarepository.save(talla);
		return newtalla;
	}
	
	@PutMapping("/size/{id}")
	public ResponseEntity<String> updateCatTalla(@RequestBody CatTalla talla, @PathVariable Long id)
			throws ResourceNotFoundException{
		
		CatTalla findTalla = CatTallarepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No encontramos ninguna talla"));
		findTalla.setMedida(talla.getMedida());
		findTalla.setDescripcion(talla.getDescripcion());
		findTalla.setTipo(talla.getTipo());
		
		CatTalla updateTalla = CatTallarepository.save(findTalla);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La talla se ha actualizado correctamente " + "" + "\"}");
		
	}
	
	@DeleteMapping("/size/{id}")
	public ResponseEntity<String> deleteCatTalla(@PathVariable Long id)throws ResourceNotFoundException{
		CatTallarepository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La talla se ha eliminado correctamente " + "" + "\"}");
		
	}
	@RequestMapping(value="/size/find/{medida}",produces = {"application/json"},method= RequestMethod.GET)
	public int findproductobymedida(@PathVariable("medida") String medida) {
		int idencontrado;	
		try {
			idencontrado = CatTallarepository.encontrarTalla(medida);
			 System.out.println(idencontrado);
			 
			 return idencontrado;
		} catch (Exception e) {
			System.out.println("*************************ERRRORRRRRRRRRRRRRRRRRRR");
		}
		return 0;
	}
}
