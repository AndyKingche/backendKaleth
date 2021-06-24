package com.api.kaleth.controller;

import java.util.List;
import java.util.Optional;

import com.api.kaleth.domain.VenCabezaFactura;
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
    FacturacionRepository VenCabezaFacturarepository;

    @GetMapping("/bill")
    public List<VenCabezaFactura> getVenCabezaFacturaes(){
    	
        List<VenCabezaFactura> VenCabezaFacturaes = VenCabezaFacturarepository.findAll();
        
        System.out.println(VenCabezaFacturaes);
        return VenCabezaFacturaes;
    }

    @GetMapping("/bill/{id}")
    public Optional<VenCabezaFactura> getVenCabezaFactura(@PathVariable Long id)
    throws ResourceNotFoundException{
        Optional<VenCabezaFactura> VenCabezaFactura = VenCabezaFacturarepository.findById(id);
        return VenCabezaFactura;
    }

    @PostMapping("/bill")
    public VenCabezaFactura createVenCabezaFactura(@RequestBody VenCabezaFactura VenCabezaFactura){
    	try {
    		 VenCabezaFactura newVenCabezaFactura = VenCabezaFacturarepository.save(VenCabezaFactura);
    	    	System.out.print(newVenCabezaFactura);
    	        return VenCabezaFactura;
    		
    	}catch (Exception e) {
			System.out.print(e);
		}
       return null;

    }

    @PutMapping("/bill/{id}")
    public ResponseEntity<String> updateVenCabezaFactura(@RequestBody VenCabezaFactura VenCabezaFactura,@PathVariable Long id )
    throws ResourceNotFoundException{
        
        VenCabezaFactura findVenCabezaFactura = VenCabezaFacturarepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No encontramos nada"));

        findVenCabezaFactura.setFechaFactu(VenCabezaFactura.getFechaFactu());
        findVenCabezaFactura.setIva(VenCabezaFactura.getIva());
        findVenCabezaFactura.setTotal(VenCabezaFactura.getTotal());
        findVenCabezaFactura.setEstado(VenCabezaFactura.getEstado());
        
        final VenCabezaFactura updatefactura = VenCabezaFacturarepository.save(findVenCabezaFactura);
        return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La factua se actualizo correctamente " + "" + "\"}");

    }

    @DeleteMapping("/bill/{id}")
    public  ResponseEntity<String> deleteFactura(@PathVariable Long id)throws ResourceNotFoundException{
        VenCabezaFacturarepository.deleteById(id);
        return ResponseEntity.ok().header("Content-Type", "application/json")
        .body("{\"mensaje\": \"La factua se elimino correctamente " + "" + "\"}");

    }
    
}
