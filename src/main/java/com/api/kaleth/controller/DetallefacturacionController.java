package com.api.kaleth.controller;

import java.util.List;
import java.util.Optional;

import com.api.kaleth.domain.VenDetalleFactura;
import com.api.kaleth.respository.DetallefacturacionRepository;

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
public class DetallefacturacionController {
    @Autowired
    DetallefacturacionRepository detallerepository;

    @GetMapping("/detail-fac")
    public List<VenDetalleFactura> getDetallesfac(){
        List<VenDetalleFactura> detalles = detallerepository.findAll();
        return detalles;
    }

    @GetMapping("/detail-fac/{id}")
    public Optional <VenDetalleFactura> getDetallefac(@PathVariable Long id)
    throws ResourceNotFoundException {
        Optional<VenDetalleFactura> detalle = detallerepository.findById(id);
        return detalle;
    }

    @PostMapping("/detail-fac")
    public VenDetalleFactura createDetalle(@RequestBody VenDetalleFactura detalle){
        VenDetalleFactura newDetalle = detallerepository.save(detalle);
        return newDetalle;
    }
    
    @PutMapping("/detail-fac/{id}")
    public ResponseEntity<String> updatedetalle(@RequestBody VenDetalleFactura detalle, @PathVariable Long id )throws ResourceNotFoundException{
        VenDetalleFactura findDetalle = detallerepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No se encontro ningun dato"));
//
//        findDetalle.setFecha(detalle.getFecha());
//        findDetalle.setCantidad(detalle.getCantidad());
//        findDetalle.setTotal(detalle.getTotal());
//        findDetalle.setProductos(detalle.getProductos());

        VenDetalleFactura updateDetalle = detallerepository.save(findDetalle);

        return ResponseEntity.ok().header("Content-Type", "application/json")
        .body("{\"mensaje\": \"El detalle se ha actualizado correctamente " + id + "\"}");

    }

    @DeleteMapping("/detail-fac/{id}")
    public ResponseEntity<String> deleteDetalle(@PathVariable Long id)
    throws ResourceNotFoundException{

        detallerepository.deleteById(id);
        return ResponseEntity.ok().header("Content-Type", "application/json")
        .body("{\"mensaje\": \"El detalle se ha eliminado correctamente " + id + "\"}");

    }
}
