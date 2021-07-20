package com.api.kaleth.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.api.kaleth.domain.CatStock;
import com.api.kaleth.domain.VenCabezaFactura;
import com.api.kaleth.respository.FacturacionRepository;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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

@RestController
@RequestMapping("/api")

public class FacturacionController {
	@Autowired
	FacturacionRepository VenCabezaFacturarepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/bill")
	public List<VenCabezaFactura> getVenCabezaFacturaes() {

		List<VenCabezaFactura> VenCabezaFacturaes = VenCabezaFacturarepository.findAll();

		System.out.println(VenCabezaFacturaes);
		return VenCabezaFacturaes;
	}

	@GetMapping("/bill/{id}")
	public Optional<VenCabezaFactura> getVenCabezaFactura(@PathVariable Long id) throws ResourceNotFoundException {
		Optional<VenCabezaFactura> VenCabezaFactura = VenCabezaFacturarepository.findById(id);
		return VenCabezaFactura;
	}

	@PostMapping("/bill")
	public VenCabezaFactura createVenCabezaFactura(@RequestBody VenCabezaFactura VenCabezaFactura) {
		try {
			VenCabezaFactura newVenCabezaFactura = VenCabezaFacturarepository.save(VenCabezaFactura);
			System.out.print(newVenCabezaFactura);
			return newVenCabezaFactura;

		} catch (Exception e) {
			System.out.print(e);
		}
		return null;

	}

	@PutMapping("/bill/{id}")
	public ResponseEntity<String> updateVenCabezaFactura(@RequestBody VenCabezaFactura VenCabezaFactura,
			@PathVariable Long id) throws ResourceNotFoundException {

		VenCabezaFactura findVenCabezaFactura = VenCabezaFacturarepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No encontramos nada"));

		findVenCabezaFactura.setFechaFactu(VenCabezaFactura.getFechaFactu());
		findVenCabezaFactura.setIva(VenCabezaFactura.getIva());
		findVenCabezaFactura.setTotal(VenCabezaFactura.getTotal());
		findVenCabezaFactura.setEstado(VenCabezaFactura.getEstado());

		final VenCabezaFactura updatefactura = VenCabezaFacturarepository.save(findVenCabezaFactura);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La factua se actualizo correctamente " + "" + "\"}");

	}

	@DeleteMapping("/bill/{id}")
	public ResponseEntity<String> deleteFactura(@PathVariable Long id) throws ResourceNotFoundException {
		VenCabezaFacturarepository.deleteById(id);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"La factua se elimino correctamente " + "" + "\"}");

	}

	@RequestMapping(value = "/bill/ticket/{idfactura}", method = RequestMethod.GET)
	@ResponseBody
	public void reporteCliente(HttpServletResponse response, @PathVariable Integer idfactura) throws Exception {

		response.setContentType("text/html");
		InputStream jrxmlInput = this.getClass().getResourceAsStream("/ticketFactura.jrxml");
		JasperDesign design = JRXmlLoader.load(jrxmlInput);
		JasperReport jasperReport = JasperCompileManager.compileReport(design);

		// consulta en ves del list

		Connection cn = jdbcTemplate.getDataSource().getConnection();
		Map<String, Object> parametro = new HashMap<String, Object>();
		parametro.put("logoImagen", "logo1.png");
		parametro.put("logoKaleth", "KALETH.png");
		parametro.put("idVenCabezafactura", idfactura);

		JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametro, cn);

		JRPdfExporter pdfExporter = new JRPdfExporter();
		pdfExporter.setExporterInput(new SimpleExporterInput(jasperprint));
		ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
		pdfExporter.exportReport();

		response.setContentType("application/pdf");
		response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
		response.setHeader("Content-Disposition", "inline; filenamejasper.pdf");

		OutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(pdfReportStream.toByteArray());
		responseOutputStream.close();
		pdfReportStream.close();

	}

	@RequestMapping(value = "/bill/reporteFecha/{fechaDesde}/{fechaHasta}", method = RequestMethod.GET)
	@ResponseBody
	public void reporteFcturacionFecha(HttpServletResponse response,
			@PathVariable(name = "fechaDesde") String fechaDesde, @PathVariable(name = "fechaHasta") String fechaHasta)
			throws Exception {

		response.setContentType("text/html");
		InputStream jrxmlInput = this.getClass().getResourceAsStream("/listaFacturasPorfecha.jrxml");
		JasperDesign design = JRXmlLoader.load(jrxmlInput);
		JasperReport jasperReport = JasperCompileManager.compileReport(design);

		// consulta en ves del list

		Connection cn = jdbcTemplate.getDataSource().getConnection();
		Map<String, Object> parametro = new HashMap<String, Object>();
		parametro.put("logoImagen", "logo1.png");
		parametro.put("logoKaleth", "KALETH.png");
		parametro.put("fechaDesde", fechaDesde);

		parametro.put("fechaHasta", fechaHasta);

		JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametro, cn);

		JRPdfExporter pdfExporter = new JRPdfExporter();
		pdfExporter.setExporterInput(new SimpleExporterInput(jasperprint));
		ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
		pdfExporter.exportReport();

		response.setContentType("application/pdf");
		response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
		response.setHeader("Content-Disposition", "inline; filenamejasper.pdf");

		OutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(pdfReportStream.toByteArray());
		responseOutputStream.close();
		pdfReportStream.close();

	}

	@RequestMapping(value = "/bill/reporteFechaLocal/{fechaDesde}/{fechaHasta}/{idPuntosVenta}", method = RequestMethod.GET)
	@ResponseBody
	public void reporteFcturacionFechaLocal(HttpServletResponse response,
			@PathVariable(name = "fechaDesde") String fechaDesde, @PathVariable(name = "fechaHasta") String fechaHasta,
			@PathVariable(name = "idPuntosVenta") Integer idPuntosVenta) throws Exception {

		response.setContentType("text/html");
		InputStream jrxmlInput = this.getClass().getResourceAsStream("/listaFacturasPorfechaLocales.jrxml");
		JasperDesign design = JRXmlLoader.load(jrxmlInput);
		JasperReport jasperReport = JasperCompileManager.compileReport(design);

		// consulta en ves del list

		Connection cn = jdbcTemplate.getDataSource().getConnection();
		Map<String, Object> parametro = new HashMap<String, Object>();
		parametro.put("logoImagen", "logo1.png");
		parametro.put("logoKaleth", "KALETH.png");
		parametro.put("fechaDesde", fechaDesde);

		parametro.put("fechaHasta", fechaHasta);
		parametro.put("idPuntosVenta", idPuntosVenta);
		JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametro, cn);

		JRPdfExporter pdfExporter = new JRPdfExporter();
		pdfExporter.setExporterInput(new SimpleExporterInput(jasperprint));
		ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
		pdfExporter.exportReport();

		response.setContentType("application/pdf");
		response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
		response.setHeader("Content-Disposition", "inline; filenamejasper.pdf");

		OutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(pdfReportStream.toByteArray());
		responseOutputStream.close();
		pdfReportStream.close();

	}

	@RequestMapping(value = "/bill/dates/{fechainicio}/{fechafin}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<VenCabezaFactura> fechainicioFechaFin(@PathVariable("fechainicio") String fechainicio,
			@PathVariable("fechafin") String fechafin) throws ParseException {
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		Date begin = formato.parse(fechainicio);
		Date end = formato.parse(fechafin);
		try {
			List<VenCabezaFactura> fechaInicioFechaFin = VenCabezaFacturarepository.facturaFechas(begin,
					end);
			

			return fechaInicioFechaFin;

		} catch (Exception e) {
			System.out.println("*************************ERRRORRRRRRRRRRRRRRRRRRR encontrarproductoStockby_codprod");
		}
		return null;

	}

}
