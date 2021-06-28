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

import com.api.kaleth.domain.PeCabezaPedido;
import com.api.kaleth.respository.CabezaPedidoRepository;



@RestController
@RequestMapping("/api")
public class CabezaPedidoController {
	 @Autowired
	    CabezaPedidoRepository cabezaPedidoRepository;

	    @GetMapping("/order")
	    public List<PeCabezaPedido> getPeCabezaPedidoes(){
	    	
	        List<PeCabezaPedido> PeCabezaPedidoes = cabezaPedidoRepository.findAll();
	        
	        System.out.println(PeCabezaPedidoes);
	        return PeCabezaPedidoes;
	    }

	    @GetMapping("/order/{id}")
	    public Optional<PeCabezaPedido> getPeCabezaPedido(@PathVariable Long id)
	    throws ResourceNotFoundException{
	        Optional<PeCabezaPedido> PeCabezaPedido = cabezaPedidoRepository.findById(id);
	        return PeCabezaPedido;
	    }

	    @PostMapping("/order")
	    public PeCabezaPedido createPeCabezaPedido(@RequestBody PeCabezaPedido PeCabezaPedido){
	    	try {
	    		 PeCabezaPedido newPeCabezaPedido = cabezaPedidoRepository.save(PeCabezaPedido);
	    	    	System.out.print(newPeCabezaPedido);
	    	        return newPeCabezaPedido;
	    		
	    	}catch (Exception e) {
				System.out.print(e);
			}
	       return null;

	    }

	    @PutMapping("/order/{id}")
	    public ResponseEntity<String> updatePeCabezaPedido(@RequestBody PeCabezaPedido PeCabezaPedido,@PathVariable Long id )
	    throws ResourceNotFoundException{
	        
	        PeCabezaPedido findPeCabezaPedido = cabezaPedidoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No encontramos nada"));

	        findPeCabezaPedido.setFechaPe(PeCabezaPedido.getFechaPe());
	       
	        findPeCabezaPedido.setTotal(PeCabezaPedido.getTotal());
	        findPeCabezaPedido.setEstado(PeCabezaPedido.getEstado());
	        
	        final PeCabezaPedido updatefactura = cabezaPedidoRepository.save(findPeCabezaPedido);
	        return ResponseEntity.ok().header("Content-Type", "application/json")
					.body("{\"mensaje\": \"La factua se actualizo correctamente " + "" + "\"}");

	    }

	    @DeleteMapping("/order/{id}")
	    public  ResponseEntity<String> deleteFactura(@PathVariable Long id)throws ResourceNotFoundException{
	        cabezaPedidoRepository.deleteById(id);
	        return ResponseEntity.ok().header("Content-Type", "application/json")
	        .body("{\"mensaje\": \"La factua se elimino correctamente " + "" + "\"}");

	    }
}
