package com.api.kaleth.security;

import java.nio.file.ClosedFileSystemException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.kaleth.domain.UsUser;
import com.api.kaleth.respository.UsuariosRepository;


@Service
public class JWTUserDetailsService implements UserDetailsService{
	@Autowired
	UsuariosRepository userRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Override
	public UserDetails loadUserByUsername(String username)  {
		///consulta del usuario existente
		
		try {
			Optional<UsUser> user = userRepository.findByName(username);
			if (user!=null) {
		 
				  return new User(username,user.get().getPassword()
				  , new
				  ArrayList<>());
				 
				
				/*
				 * return new
				 * org.springframework.security.core.userdetails.User(user.get().getNombre(),
				 * bcryptEncoder.encode(user.get().getPassword()), new ArrayList<>());
				 */
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al generar token"+e);
			return null;
		}
		
	}
}
