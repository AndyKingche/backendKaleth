package com.api.kaleth.reports.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.api.kaleth.domain.VenCliente;
import com.api.kaleth.reports.repository.exportPDF;
import com.api.kaleth.respository.ClientesRepository;

import java.io.OutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class exportReport {
	/*
	 * @Autowired exportPDF exportPDF;
	 * 
	 * public void exportReporte(OutputStream out) throws FileNotFoundException,
	 * SQLException, JRException { JasperPrint jasperPrint =
	 * exportPDF.reporteClientes();
	 * JasperExportManager.exportReportToPdfStream(jasperPrint, out); }
	 */
	@Autowired
	ClientesRepository clientesRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException, SQLException {
        String path = "C:\\Users\\user\\Desktop";
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:listaClientes.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        //JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Connection cn = jdbcTemplate.getDataSource().getConnection();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn );
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
        }

        return "report generated in path : " + path;
    }
	
}
