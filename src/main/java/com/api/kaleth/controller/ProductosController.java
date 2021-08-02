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
import com.api.kaleth.respository.ProductosRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ProductosController {
	@Autowired
	ProductosRepository CatProductorepository;

	@GetMapping("/products")
	public List<CatProducto> getCatProducto() {
		List<CatProducto> CatProducto = CatProductorepository.findAll();
		System.out.println(CatProducto);
		return CatProducto;
	}

	@GetMapping("/products/{id}")
	public Optional<CatProducto> getProducto(@PathVariable Long id) throws ResourceNotFoundException {

		Optional<CatProducto> producto = CatProductorepository.findById(id);

		return producto;

	}

	@PostMapping("/products")
	public CatProducto createCatProducto(@RequestBody CatProducto CatProducto) {

		CatProducto newProducto = CatProductorepository.save(CatProducto);

		return newProducto;
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<String> updateCatProducto(@RequestBody CatProducto CatProducto, @PathVariable Long id)
			throws ResourceNotFoundException {

		CatProducto findproducto = CatProductorepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No encontramos el producto"));

		findproducto.setCatCategoria(CatProducto.getCatCategoria());
		findproducto.setCatDiseno(CatProducto.getCatDiseno());
		findproducto.setCatTalla(CatProducto.getCatTalla());
		
		findproducto.setCodProducto(CatProducto.getCodProducto());

		CatProducto updateCatProducto = CatProductorepository.save(findproducto);

		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El producto se ha actualizado correctamente " + id + "\"}");
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteCatProducto(@PathVariable Long id) throws ResourceNotFoundException {

		CatProductorepository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El producto se ha eliminado correctamente " + id + "\"}");

	}
	
	@RequestMapping(value="/products/find/{codproducto}",produces = {"application/json"},method= RequestMethod.GET)
	public int findproductobycod(@PathVariable("codproducto") String codproducto) {
		int encontrado = 0;	
		try {
			encontrado = CatProductorepository.findBycodigo(codproducto);
			 System.out.println(encontrado);
			 
			 return encontrado;
		} catch (Exception e) {
			System.out.println("*************************ERRRORRRRRRRRRRRRRRRRRRR");
		}
		return -1;
	}
	
	@RequestMapping(value="/products/findid/{idproducto}",produces = {"application/json"},method= RequestMethod.GET)
	public CatProducto findproductobyId(@PathVariable("idproducto") Integer idproducto) {
		CatProducto encontrado;	
		try {
			encontrado = CatProductorepository.productoId(idproducto);
			 System.out.println(encontrado);
			 
			 return encontrado;
		} catch (Exception e) {
			System.out.println("*************************ERRRORRRRRRRRRRRRRRRRRRR");
		}
		return null;
	}
	
}
