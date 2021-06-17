package com.api.kaleth.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.kaleth.domain.UsRole;
import com.api.kaleth.security.enums.RolNombre;
import com.api.kaleth.security.repository.RolRepository;

@Service
@Transactional
public class RolService {

	@Autowired
	RolRepository rolRepository;

	public Optional<UsRole> getByRolNombre(RolNombre rolNombre ){
		return rolRepository.findbyRolNombre(rolNombre);		
	}
	
	  public void save(UsRole rol){
	        rolRepository.save(rol);
	    }
}
