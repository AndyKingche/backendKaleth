package com.api.kaleth.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

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

import com.api.kaleth.domain.CatProducto;
import com.api.kaleth.domain.CatStock;
import com.api.kaleth.respository.cat_stockRepository;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

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
public class cat_stockController {
	@Autowired
	cat_stockRepository cat_stockRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/stock")
	public List<CatStock> getCatDiseno() {
		List<CatStock> CatStock = cat_stockRepository.findAll();
		return CatStock;

	}

	@GetMapping("/stock/{id}")
	public Optional<CatStock> getStock(@PathVariable Long id) throws ResourceNotFoundException {
		Optional<CatStock> OneStock = cat_stockRepository.findById(id);

		return OneStock;
	}

	@PostMapping("/stock")
	public CatStock createCatStock(@RequestBody CatStock CatStock) {

		CatStock newStock = cat_stockRepository.save(CatStock);

		return newStock;
	}

	@RequestMapping(value = "/stock/number/{id_producto}/{id_puntoventa}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public int encontrarStock(@PathVariable("id_producto") Integer id_producto,
			@PathVariable("id_puntoventa") Integer id_puntoventa) {
		int numeroTipo = 0;
		try {
			numeroTipo = cat_stockRepository.encontrarStock(id_producto, id_puntoventa);
			System.out.println(numeroTipo);

			return numeroTipo;
		} catch (Exception e) {
			System.out.println("*************************ERRRORRRRRRRRRRRRRRRRRRR");
		}
		return -1;
	}

	@RequestMapping(value = "/stock/updateRest/{id_producto}/{id_puntoventa}/{cantidad}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public int UpdateStock(@PathVariable("id_producto") Integer id_producto,
			@PathVariable("id_puntoventa") Integer id_puntoventa, @PathVariable("cantidad") Integer cantidad) {
		int actualizado = 0;
		try {
			actualizado = cat_stockRepository.actualizarStockRestar(cantidad, id_producto, id_puntoventa);
			System.out.println(actualizado);

			return actualizado;
		} catch (Exception e) {
			System.out.println("*************************ERRRORRRRRRRRRRRRRRRRRRR");
		}
		return -1;
	}

	@PutMapping("/stock/{id}")
	public ResponseEntity<String> updateStock(@RequestBody CatStock stock, @PathVariable Long id)
			throws ResourceNotFoundException {

		CatStock findStock = cat_stockRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id"));

		findStock.setCantidad(stock.getCantidad());
		findStock.setStockMax(stock.getStockMax());
		findStock.setStockMin(stock.getStockMin());
		findStock.setPrecioDistribuidor(stock.getPrecioDistribuidor());
		findStock.setPrecioMayor(stock.getPrecioMayor());
		findStock.setPrecioUnit(stock.getPrecioUnit());
		findStock.setExiste(stock.getExiste());

		CatStock updateStock = cat_stockRepository.save(findStock);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Stock se ha actualizado correctamente " + "" + "\"}");
	}

	@DeleteMapping("/stock/{id}")
	public ResponseEntity<String> deleteStock(@PathVariable Long id) throws ResourceNotFoundException {

		CatStock findStock = cat_stockRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No se encontro el id"));
		cat_stockRepository.deleteById(id);

		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Stock se ha eliminado correctamente " + id + "\"}");
	}

	@RequestMapping(value = "/stock/find/{id_producto}/{id_puntoventa}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<CatStock> findStockIdProIdpPuntV(@PathVariable("id_producto") Integer idproducto,
			@PathVariable("id_puntoventa") Integer idpuntoventa) {

		try {
			List<CatStock> productostock = cat_stockRepository.findbyIdProductoIdPuntosVenta(idproducto, idpuntoventa);
			System.out.println(productostock);

			return productostock;
		} catch (Exception e) {
			System.out.println("*************************ERRRORRRRRRRRRRRRRRRRRRR");
		}
		return null;
	}

	@RequestMapping(value = "/stock/updates/{id_producto}/{id_puntoventa}/{cantidad}/"
			+ "{precioUnit}/{precioMayor}/{precioDist}/{stockMax}/{stockMin}/{existe}", produces = {
					"application/json" }, method = RequestMethod.GET)
	public int UpdateStocks(@PathVariable("id_producto") Integer id_producto,
			@PathVariable("id_puntoventa") Integer id_puntoventa, @PathVariable("cantidad") Integer cantidad,
			@PathVariable("precioUnit") Float precioUnit, @PathVariable("precioMayor") Float precioMayor,
			@PathVariable("precioDist") Float precioDist, @PathVariable("stockMax") Integer stockMax,
			@PathVariable("stockMin") Integer stockMin,@PathVariable("existe") String existe) {
		int actualizado = 0;

		try {
			actualizado = cat_stockRepository.actualizarStocks(cantidad, precioUnit, precioMayor, precioDist, stockMax,
					stockMin, existe,id_producto, id_puntoventa);
			System.out.println(actualizado);
			return actualizado;
		} catch (Exception e) {
			System.out.println("*************************ERRRORRRRRRRRRRRRRRRRRRR");
		}
		return -1;
	}

	@RequestMapping(value = "/stock/product/{codproducto}/{idpuntoventa}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<CatStock> encontrarproductoStockby_codprod(@PathVariable("codproducto") String codproducto,
			@PathVariable("idpuntoventa") Integer idpuntoventa) {

		try {
			List<CatStock> productoStock = cat_stockRepository.findProductoStockby_codProducto(codproducto,
					idpuntoventa);

			return productoStock;
		} catch (Exception e) {
			System.out.println("*************************ERRRORRRRRRRRRRRRRRRRRRR encontrarproductoStockby_codprod");
		}
		return null;
	}

	@RequestMapping(value = "/stock/exist/{incio}/{numeroFilas}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<CatStock> getStockAllExist(@PathVariable("incio") Integer inicio,
			@PathVariable("numeroFilas") Integer numeroFilas) {
		List<CatStock> CatStock = cat_stockRepository.findAllExitents(inicio, numeroFilas);
		return CatStock;

	}

	@RequestMapping(value = "/stock/exist/{id}/{incio}/{numeroFilas}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<CatStock> getStockAllExistPuntoVenta(@PathVariable("id") Integer id,
			@PathVariable("incio") Integer inicio, @PathVariable("numeroFilas") Integer numeroFilas) {
		List<CatStock> CatStock = cat_stockRepository.findAllExitentsPuntoVenta(id, inicio, numeroFilas);
		return CatStock;

	}

	@RequestMapping(value = "/stock/cant", produces = { "application/json" }, method = RequestMethod.GET)
	public int getStockAllExist() {
		int numeroExistentes = cat_stockRepository.findCantidadAllExitents();
		return numeroExistentes;

	}

	// Consultar del Administrador
	@RequestMapping(value = "/stock/findInventario", produces = { "application/json" }, method = RequestMethod.GET)
	public List<CatStock> findStockInventario() {
		try {
			List<CatStock> findStock = cat_stockRepository.findInventario();
			
			return findStock;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error al consultar en el stock FindInventario");
		}

		return null;

	}

	// consulta inventario de acuerdo al local que se encuentra
	@RequestMapping(value = "/stock/findInventario/{puntoventa}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<CatStock> findStockbyPuntoVenta(@PathVariable Integer puntoventa) {
		try {
			List<CatStock> findStock = cat_stockRepository.findInventarioWhereIdPuntosVenta(puntoventa);
			return findStock;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error al consultar en el stock FindInventario por punto vent");
		}

		return null;
	}

// consulta cuando la cantidad es inferior o igual al stcokMinimo
	@RequestMapping(value = "/stock/findMin", produces = { "application/json" }, method = RequestMethod.GET)
	public List<CatStock> findStockbyMin() {
		try {
			List<CatStock> findStock = cat_stockRepository.findPedidoStockMin();
			return findStock;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error al consultar en el stock FindInventario stock min");
		}

		return null;
	}

	// consulta cuando la cantidad es inferior o igual al stcokMinimo solo de un
	// local
	@RequestMapping(value = "/stock/findMin/{puntoventa}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<CatStock> findStockbyMinPuntoVenta(@PathVariable Integer puntoventa) {
		try {
			List<CatStock> findStock = cat_stockRepository.findPedidoStockMinWhereIdPuntosVenta(puntoventa);
			return findStock;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error al consultar en el stock FindInventario stock min por punto vent");
		}

		return null;
	}

	// consultar producto de acuerdo a diferentes parametros
	@RequestMapping(value = "/stock/findparameteros/{parametros}", method = RequestMethod.GET)
	public List<CatStock> findStockbyParameters(@PathVariable(name = "parametros") String parametros) throws Exception {
		System.out.println("0000si entre" + parametros);
		try {

			List<CatStock> findStock = cat_stockRepository.findProductByAllParameters(parametros);
			System.out.println(findStock);
			return findStock;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error al consultar en el stock FindInventario por multiples parametros");
			return null;
		}

	}

	// consultar producto de acuerdo a diferentes parametros
	@RequestMapping(value = "/stock/findparameterospuntosventa/{parametros}/{puntosventa}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<CatStock> findStockbyParametersPuntoVenta(@PathVariable(name = "puntosventa") Integer puntosventa,
			@PathVariable(name = "parametros") String parametros) {
		try {
			List<CatStock> findStock = cat_stockRepository.findProductByAllParametersWhereIdPuntosVenta(puntosventa,
					parametros, parametros, parametros, parametros, parametros, parametros);
			return findStock;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error al consultar en el stock FindInventario por multiples parametros por punto vent");
		}

		return null;
	}
	@RequestMapping(value = "/stock/demo", produces = { "application/json" }, method = RequestMethod.GET)
	public String getStockdemo() {
		return "hOL";
	}
	@RequestMapping(value = "/stock/reportTotal",produces = {
	"application/json"},method = RequestMethod.GET)
	@ResponseBody
	public List<String> reporteStockTotal(HttpServletResponse response) throws Exception {
		
		try {
			
			//response.setContentType("text/html"); 
				
			//String urlLink = request.getRequestURL().toString();
				List<String> respuesta = new ArrayList<String>();
			  byte[] bytes = null; 
			  String pdfBase64 = ""; 
			  InputStream jrxmlInput = this.getClass().getResourceAsStream("/inventarioTo.jrxml"); 
			  JasperDesign design = JRXmlLoader.load(jrxmlInput); 
			  JasperReport jasperReport = JasperCompileManager.compileReport(design);
			 
			  
			  // consulta en ves del list
			  
				
				  Connection cn = jdbcTemplate.getDataSource().getConnection();
				  
				  Map<String, Object> parametro = new HashMap<String, Object>();
				  parametro.put("logoImagen", "logo1.png"); parametro.put("logoKaleth",
				  "KALETH.png");
				  
				  JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,
				  parametro, cn);
				 
			  
				
					/*
					 * JRPdfExporter pdfExporter = new JRPdfExporter();
					 * pdfExporter.setExporterInput(new SimpleExporterInput(jasperprint));
					 * ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
					 * pdfExporter.setExporterOutput(new
					 * SimpleOutputStreamExporterOutput(pdfReportStream));
					 * pdfExporter.exportReport();
					 */
				 
			  
				
				  
				 
			  
				
					/*
					 * response.setContentType("application/pdf");
					 * response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
					 * response.setHeader("Content-Disposition", "inline; filenamejasper.pdf");
					 * 
					 * OutputStream responseOutputStream = response.getOutputStream();
					 * responseOutputStream.write(pdfReportStream.toByteArray()); //Obtener PDF
					 * responseOutputStream.close(); pdfReportStream.close();
					 */
				  
				  bytes = JasperExportManager.exportReportToPdf(jasperprint); 
				  pdfBase64 =
				  Base64.getEncoder().encodeToString(bytes);
				  response.setContentType("application/pdf");
				  respuesta.add(pdfBase64);	
				  
				  return  respuesta;
					  
						  /*ResponseEntity
					      .ok()
					      // Specify content type as PDF
					      .header("Content-Type", "application/pdf; charset=UTF-8")
					      // Tell browser to display PDF if it can
					      .header("Content-Disposition", "inline; filename=\"" + "holi"+ ".pdf\"")
					      .body(bytes);*/

			
		} catch (Exception e) {
			System.out.println("ERROR AL GENERAR REPORTE TOTAL");
			return null;
		}
		
		
		

	}

	@RequestMapping(value = "/stock/report/{idpuntoventa}", method = RequestMethod.GET)
	@ResponseBody
	public List<String> reporteStockPuntoVenta(HttpServletResponse response, @PathVariable Integer idpuntoventa)
			throws Exception {

		try {
			//response.setContentType("text/html");
			List<String> respuesta = new ArrayList<String>();
			  byte[] bytes = null; 
			  String pdfBase64 = ""; 
			InputStream jrxmlInput = this.getClass().getResourceAsStream("/inventarioLocales.jrxml");
			JasperDesign design = JRXmlLoader.load(jrxmlInput);
			JasperReport jasperReport = JasperCompileManager.compileReport(design);

			// consulta en ves del list

			Connection cn = jdbcTemplate.getDataSource().getConnection();

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("logoImagen", "logo1.png");
			parametro.put("logoKaleth", "KALETH.png");
			parametro.put("idPuntosVenta", idpuntoventa);

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
			 * response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
			 * response.setHeader("Content-Disposition", "inline; filenamejasper.pdf");
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
			System.out.println("ERROR AL GENERAR REPORTE TOTAL x punto venta");
			return null;
		}
		

	}

	
	@RequestMapping(value = "/stock/codigoBarra", method = RequestMethod.GET)
	@ResponseBody
	public List<String>  codigoBarraStock(HttpServletResponse response)
			throws Exception {
		
		try {
			//response.setContentType("text/html");
			List<String> respuesta = new ArrayList<String>();
			  byte[] bytes = null; 
			  String pdfBase64 = ""; 
			InputStream jrxmlInput = this.getClass().getResourceAsStream("/inventarioCodigoBarras.jrxml");
			JasperDesign design = JRXmlLoader.load(jrxmlInput);
			JasperReport jasperReport = JasperCompileManager.compileReport(design);

			// consulta en ves del list

			Connection cn = jdbcTemplate.getDataSource().getConnection();

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("logoImagen", "logo1.png");
			parametro.put("logoKaleth", "KALETH.png");
			

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
			 * response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
			 * response.setHeader("Content-Disposition", "inline; filenamejasper.pdf");
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
			System.out.println("ERROR AL GENERAR reporte codigos de barra"+e);
			return null;
		}
		

	}

	@RequestMapping(value = "/stock/report/minTotal", method = RequestMethod.GET)
	@ResponseBody
	public List<String> stockMinTotal(HttpServletResponse response)
			throws Exception {
		try {
			//response.setContentType("text/html");
			response.setContentType("application/pdf"); 
			List<String> respuesta = new ArrayList<String>();
			  byte[] bytes = null; 
			  String pdfBase64 = ""; 
			InputStream jrxmlInput = this.getClass().getResourceAsStream("/StockMinTotal.jrxml");
			
			  JasperDesign design = JRXmlLoader.load(jrxmlInput); 
			  JasperReport jasperReport = JasperCompileManager.compileReport(design);
			  
			  // consulta en ves del list
			  
			  Connection cn = jdbcTemplate.getDataSource().getConnection();
			  
			  Map<String, Object> parametro = new HashMap<String, Object>();
			  parametro.put("logoImagen", "logo1.png"); 
			  parametro.put("logoKaleth", "KALETH.png");
			  
			  
			  JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,
			  parametro, cn);
			 

			/*
			 * JRPdfExporter pdfExporter = new JRPdfExporter();
			 * pdfExporter.setExporterInput(new SimpleExporterInput(jasperprint));
			 * ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
			 * pdfExporter.setExporterOutput(new
			 * SimpleOutputStreamExporterOutput(pdfReportStream));
			 * pdfExporter.exportReport();
			 * 
			 * response.setContentType("application/pdf");
			 * response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
			 * response.setHeader("Content-Disposition", "inline; filenamejasper.pdf");
			 * 
			 * OutputStream responseOutputStream = response.getOutputStream();
			 * responseOutputStream.write(pdfReportStream.toByteArray());
			 * responseOutputStream.close(); pdfReportStream.close();
			 */
			
			  bytes = JasperExportManager.exportReportToPdf(jasperprint); 
			  pdfBase64 = Base64.getEncoder().encodeToString(bytes);
			  
			  respuesta.add(pdfBase64);
			 
			 
			  return  respuesta;
			

		} catch (Exception e) {
			System.out.println("ERROR AL GENERAR reporte minimo total"+e);
			return null;
		}
		
	}
	
	@RequestMapping(value = "/stock/report/minTotalPoints/{idpuntoventa}", method = RequestMethod.GET)
	@ResponseBody
	public List<String> minTotalPoints(HttpServletResponse response, @PathVariable Integer idpuntoventa)
			throws Exception {
		try {
			//response.setContentType("text/html");
			List<String> respuesta = new ArrayList<String>();
			  byte[] bytes = null; 
			  String pdfBase64 = "";
			InputStream jrxmlInput = this.getClass().getResourceAsStream("/StockMinTotalLocal.jrxml");
			JasperDesign design = JRXmlLoader.load(jrxmlInput);
			JasperReport jasperReport = JasperCompileManager.compileReport(design);

			// consulta en ves del list

			Connection cn = jdbcTemplate.getDataSource().getConnection();

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("logoImagen", "logo1.png");
			parametro.put("logoKaleth", "KALETH.png");
			parametro.put("idPuntosVenta", idpuntoventa);

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
			 * response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
			 * response.setHeader("Content-Disposition", "inline; filenamejasper.pdf");
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
			System.out.println("ERROR AL GENERAR reporte minimo total x punto venta"+e);
			return null;
		}
		

	}
	
}
