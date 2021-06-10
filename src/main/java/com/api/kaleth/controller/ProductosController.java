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

import com.api.kaleth.domain.Productos;
import com.api.kaleth.respository.ProductosRepository;

@RestController
@RequestMapping("/api")
public class ProductosController {
	@Autowired
	ProductosRepository productosrepository;
	
	@GetMapping("/products")
	public List<Productos> getProductos(){
		List<Productos> productos = productosrepository.findAll();
		return productos;
	}
	
	@GetMapping("/products/{id}")
	public Optional<Productos> getProducto(@PathVariable Long id)
	throws ResourceNotFoundException{
		
		Optional<Productos> producto = productosrepository.findById(id);
		
		return producto;
		
	}
	
	@PostMapping("/products")
	public Productos createProductos(@RequestBody Productos productos) {
		
		Productos newProducto = productosrepository.save(productos);
		
		return newProducto;
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<String> updateProductos(@RequestBody Productos productos, @PathVariable Long id)
	throws ResourceNotFoundException{
		
		Productos findproducto = productosrepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No encontramos el producto"));
		
		findproducto.setCategoria(productos.getCategoria());
		findproducto.setDisenos(productos.getDisenos());
		findproducto.setTallas(productos.getTallas());
		
		Productos updateProductos = productosrepository.save(findproducto);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El producto se ha actualizado correctamente " + id + "\"}");
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProductos(@PathVariable Long id)
	throws ResourceNotFoundException{
		
		productosrepository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
		.body("{\"mensaje\": \"El producto se ha eliminado correctamente " + id + "\"}");
		
	}
}
