package com.api.kaleth.controller;

import java.util.List;
import java.util.Optional;

import com.api.kaleth.domain.Facturacion;
import com.api.kaleth.respository.FacturacionRepository;

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

@RestController
@RequestMapping("/api")

public class FacturacionController {
    @Autowired
    FacturacionRepository facturacionrepository;

    @GetMapping("/bill")
    public List<Facturacion> getFacturaciones(){
        List<Facturacion> facturaciones = facturacionrepository.findAll();
        return facturaciones;
    }

    @GetMapping("/bill/{id}")
    public Optional<Facturacion> getFacturacion(@PathVariable Long id)
    throws ResourceNotFoundException{
        Optional<Facturacion> facturacion = facturacionrepository.findById(id);
        return facturacion;
    }

    @PostMapping("/bill")
    public Facturacion createFacturacion(@RequestBody Facturacion facturacion){
        Facturacion newFacturacion = facturacionrepository.save(facturacion);

        return newFacturacion;

    }

    @PutMapping("/bill/{id}")
    public ResponseEntity<String> updateFacturacion(@RequestBody Facturacion facturacion,@PathVariable Long id )
    throws ResourceNotFoundException{
        
        Facturacion findfacturacion = facturacionrepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No encontramos nada"));

        findfacturacion.setFecha(facturacion.getFecha());
        findfacturacion.setIva(facturacion.getIva());
        
        final Facturacion updatefactura = facturacionrepository.save(findfacturacion);
        return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La factua se actualizo correctamente " + "" + "\"}");

    }

    @DeleteMapping("/bill/{id}")
    public  ResponseEntity<String> deleteFactura(@PathVariable Long id)throws ResourceNotFoundException{
        facturacionrepository.deleteById(id);
        return ResponseEntity.ok().header("Content-Type", "application/json")
        .body("{\"mensaje\": \"La factua se elimino correctamente " + "" + "\"}");

    }
    
}
