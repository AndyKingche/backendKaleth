package com.api.kaleth.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.api.kaleth.domain.UsRole;
import com.api.kaleth.security.enums.RolNombre;
import com.api.kaleth.security.service.RolService;

@Component
public class CreateRoles implements CommandLineRunner {
	
	@Autowired
	RolService rolservice;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		UsRole roladmin = new UsRole(RolNombre.ROLE_ADMIN);
		UsRole rolcajero = new UsRole(RolNombre.ROLE_CAJERO);
		UsRole roluser = new UsRole(RolNombre.ROLE_USER);
		
		rolservice.save(roladmin);
		rolservice.save(rolcajero);
		rolservice.save(roluser);
		
	}

}
