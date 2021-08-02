package com.api.kaleth.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.PeCabezaPedido;
import com.api.kaleth.respository.CabezaPedidoRepository;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CabezaPedidoController {
	 @Autowired
	    CabezaPedidoRepository cabezaPedidoRepository;
	 @Autowired
	 JdbcTemplate jdbcTemplate;

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
	    
	    @RequestMapping(value="/order/report/{idCliente}/{idCabezaPedido}",method= RequestMethod.GET)
		@ResponseBody 
		public List<String> reporteStockPuntoVenta(HttpServletResponse response, 
				@PathVariable(name="idCabezaPedido") Integer idCabezaPedido,
				@PathVariable(name="idCliente") Integer idCliente) throws Exception{
			try {
				//response.setContentType("text/html");
				List<String> respuesta = new ArrayList<String>();
				  byte[] bytes = null; 
				  String pdfBase64 = "";
				InputStream jrxmlInput =  this.getClass().getResourceAsStream("/proformaPedido.jrxml");
				JasperDesign design = JRXmlLoader.load(jrxmlInput);
				JasperReport jasperReport = JasperCompileManager.compileReport(design);
				
				  //consulta en ves del list 
					
				Connection cn = jdbcTemplate.getDataSource().getConnection(); 
						
						
				Map<String,Object> parametro = new HashMap<String, Object>();
			parametro.put("logoImagen", "logo1.png"); 
			parametro.put("logoKaleth","KALETH.png"); 
			parametro.put("idCliente", idCliente);
			parametro.put("idCabezaPedido", idCabezaPedido);
			JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametro, cn);
						  
							/*
							 * JRPdfExporter pdfExporter = new JRPdfExporter();
							 * pdfExporter.setExporterInput(new SimpleExporterInput(jasperprint));
							 * ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
							 * pdfExporter.setExporterOutput(new
							 * SimpleOutputStreamExporterOutput(pdfReportStream));
							 * pdfExporter.exportReport();
							 * 
							 * response.setContentType("application/pdf");
							 * response.setHeader("Content-Length",String.valueOf(pdfReportStream.size()));
							 * response.setHeader("Content-Disposition","inline; filenamejasper.pdf");
							 * 
							 * OutputStream responseOutputStream = response.getOutputStream();
							 * responseOutputStream.write(pdfReportStream.toByteArray());
							 * responseOutputStream.close(); pdfReportStream.close();
							 */	
			bytes = JasperExportManager.exportReportToPdf(jasperprint); 
			  pdfBase64 =
			  Base64.getEncoder().encodeToString(bytes);
			  response.setContentType("application/pdf");
			  respuesta.add(pdfBase64);	
			  
			  return  respuesta;	  
						  
			} catch (Exception e) {
				System.out.println("Error al gnerar pdf pedido");
				return null;
			}
			
				 
				 
		}
	    
}
