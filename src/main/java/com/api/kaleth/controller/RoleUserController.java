package com.api.kaleth.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.api.kaleth.domain.UsRoleUser;
import com.api.kaleth.domain.UsRoleUserPk;
import com.api.kaleth.respository.RoleUserRepository;

@RestController
@RequestMapping("/api")
public class RoleUserController {
	@Autowired
	RoleUserRepository roleuserRepository;
	
	@GetMapping("/rol-user")
	public List<UsRoleUser> getUsRole(){
		List<UsRoleUser> UsRole = roleuserRepository.findAll();
		return UsRole;
	}
	
	@GetMapping("/rol-user/{id}")
	public List<UsRoleUser> getRoluser(@PathVariable Integer id) throws ResourceNotFoundException{
		
		List<UsRoleUser> roluser = roleuserRepository.findByIdUsuario(id);
	
		return roluser;
	}
	
	@PostMapping("/rol-user")
	public UsRoleUser createRole(@RequestBody UsRoleUser rol) {
		return roleuserRepository.save(rol);
	}
}
