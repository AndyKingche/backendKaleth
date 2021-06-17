package com.api.kaleth.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.kaleth.domain.UsUser;
import com.api.kaleth.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	public Optional<UsUser> getByNombreUsuario(String nombreUsuario){
		return usuarioRepository.findbyNombreUsuario(nombreUsuario); 
	}
	
	public boolean existsbyNombreUsuario(String nombreUsuario) {
		return usuarioRepository.existsbyNombreUsuario(nombreUsuario);
	}
	public boolean existsbyEmail(String email) {
		return usuarioRepository.existsbyEmail(email);
	}
	
	public void save(UsUser usuario) {
		usuarioRepository.save(usuario);
	}
}
