package com.api.kaleth.reports.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


@Repository
public class exportPDF {

	public JdbcTemplate jdbcTemplate = new JdbcTemplate();
	public JasperPrint reporteClientes() throws SQLException, FileNotFoundException {
		try {

			System.out.println("entreeee");

			File file = ResourceUtils.getFile("classpath:listaClientes.jrxml");
			JasperReport reporte = JasperCompileManager.compileReport(file.getAbsolutePath());
			Connection cn = jdbcTemplate.getDataSource().getConnection();
			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("logo1", "LogoNaturista.jpg");

			JasperPrint j = JasperFillManager.fillReport(reporte, parametro, cn);
		
			return j;

		} catch (JRException e) {
			// e.printStackTrace();
			return null;
		}
		

	}
}
