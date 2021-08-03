package com.api.kaleth.security;

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
		Optional<UsUser> user = userRepository.findByName(username);
		System.out.print(username);
		if (user!=null) {
			
			  System.out.println("si entre"); 
			  return new User(username,bcryptEncoder.encode(user.get().getPassword())
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
	}
}
