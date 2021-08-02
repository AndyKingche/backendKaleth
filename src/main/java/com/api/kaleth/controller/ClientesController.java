package com.api.kaleth.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;
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
import org.springframework.web.servlet.ModelAndView;

import com.api.kaleth.domain.VenCliente;
import com.api.kaleth.reports.repository.exportPDF;
import com.api.kaleth.reports.service.exportReport;
import com.api.kaleth.respository.ClientesRepository;
import java.io.OutputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ClientesController {

	@Autowired
	ClientesRepository VenClienteRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	exportReport exportReport;
	
	final Logger log = LoggerFactory.getLogger(this.getClass());
    final ModelAndView model = new ModelAndView();
	@GetMapping("/client")
	public List<VenCliente> getVenCliente(){
		List<VenCliente> VenClientes = VenClienteRepository.findAll();
		
	
		return VenClientes;
	}
	
	@GetMapping("/client/{id}")
	public Optional<VenCliente> getCliente(@PathVariable Long id)throws ResourceNotFoundException{
		
		Optional<VenCliente> VenCliente = VenClienteRepository.findById(id);
		return VenCliente;
				
	}
	
	@PostMapping("/client")
	public VenCliente createVenCliente(@RequestBody VenCliente VenCliente) {
		VenCliente newVenCliente = VenClienteRepository.save(VenCliente);
		
		return newVenCliente;
	}
	
	@PutMapping("/client/{id}")
	public ResponseEntity<String> updateVenCliente(@RequestBody VenCliente VenCliente, @PathVariable Long id)
	throws ResourceNotFoundException{
		VenCliente findVenCliente = VenClienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No encontramos ningun id"));
		
		findVenCliente.setNombreCli(VenCliente.getNombreCli());
		findVenCliente.setApellidoCli(VenCliente.getApellidoCli());
		findVenCliente.setCedulaCli(VenCliente.getCedulaCli());
		findVenCliente.setDireccionCli(VenCliente.getDireccionCli());
		findVenCliente.setEmail(VenCliente.getEmail());
		findVenCliente.setTelefono(VenCliente.getTelefono());
		
		VenCliente updateVenCliente = VenClienteRepository.save(findVenCliente);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La VenCliente se ha actualizado correctamente " + id + "\"}");
	}
	
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<String> deleteVenCliente(@PathVariable Long id){
		
		VenClienteRepository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
		.body("{\"mensaje\": \"La VenCliente se ha eliminado correctamente " + id + "\"}");
	}
	
	@RequestMapping(value="/client/findcedula/{cedula}", produces = {"application/json"},method= RequestMethod.GET)
	public List<VenCliente> findbyCedula(@PathVariable("cedula") String cedula){
		
		try {
			List<VenCliente> clienteencontrado = VenClienteRepository.clientesByCedula(cedula);
			return clienteencontrado;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	/*
	 * @RequestMapping(value = "/exportPdf", method = RequestMethod.GET) public void
	 * getVenClientePdf(HttpServletResponse response) throws IOException,
	 * SQLException, JRException{ response.setContentType("/aplication/x-download");
	 * response.setHeader("Content-Disposition",String.
	 * format("attachment; filename=\"person.pdf\""));
	 * 
	 * OutputStream out = response.getOutputStream();
	 * 
	 * exportReport.exportReporte(out);
	 * 
	 * }
	 */
	
//	@GetMapping("/client/pdfCliente")
	/*
	 * @RequestMapping(value = "/client/pdfCliente", produces =
	 * {"application/x-download"}, method = RequestMethod.GET) public void
	 * generateReport(ModelAndView model,HttpServletResponse response) throws
	 * JRException, SQLException, IOException { JasperPrint jasperPrint = null;
	 * 
	 * response.setContentType("application/x-download");
	 * response.setHeader("Content-Disposition",
	 * String.format("attachment; filename=\"users.pdf\""));
	 * 
	 * OutputStream out = response.getOutputStream(); jasperPrint =
	 * exportReport.exportReport();
	 * JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	 * 
	 * }
	 */

	@GetMapping("/client/pdfCliente")
    public void generateReport(HttpServletResponse response) throws JRException, SQLException, IOException {
		response.setContentType("/aplication/x-download");
		 response.setHeader("Content-Disposition",String.format("attachment; filename=\"cliente.pdf\""));
		  
		 OutputStream out = response.getOutputStream();
		 
		 exportReport.exportReporte(out);
    }
	
	@RequestMapping(value="/client/report",method= RequestMethod.GET)
	@ResponseBody 
	public void reporteCliente(HttpServletResponse response) throws Exception{
		
		
		response.setContentType("text/html");
		InputStream jrxmlInput =  this.getClass().getResourceAsStream("/listaClientes.jrxml");
		JasperDesign design = JRXmlLoader.load(jrxmlInput);
		JasperReport jasperReport = JasperCompileManager.compileReport(design);
		
		  //consulta en ves del list 
			
			  Connection cn = jdbcTemplate.getDataSource().getConnection(); 
			  Map<String,Object> parametro = new HashMap<String, Object>();
			  parametro.put("logoImagen", "logo1.png");
			  parametro.put("logoKaleth", "KALETH.png");
			  
			  JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,
			  parametro, cn);
			  
			  JRPdfExporter pdfExporter = new JRPdfExporter();
			  pdfExporter.setExporterInput(new SimpleExporterInput(jasperprint));
			  ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
			  pdfExporter.setExporterOutput(new
			  SimpleOutputStreamExporterOutput(pdfReportStream));
			  pdfExporter.exportReport();
			  
			  response.setContentType("application/pdf");
			  response.setHeader("Content-Length",String.valueOf(pdfReportStream.size()));
			  response.setHeader("Content-Disposition","inline; filenamejasper.pdf");
			  
			  OutputStream responseOutputStream = response.getOutputStream();
			  responseOutputStream.write(pdfReportStream.toByteArray());
			  responseOutputStream.close(); pdfReportStream.close();
			 
	}
	
	
  
}
