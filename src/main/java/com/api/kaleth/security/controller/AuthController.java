package com.api.kaleth.security.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.UsRole;
import com.api.kaleth.domain.UsUser;
import com.api.kaleth.security.dto.JWTDTO;
import com.api.kaleth.security.dto.LoginUsuario;
import com.api.kaleth.security.dto.NuevoUsuario;
import com.api.kaleth.security.enums.RolNombre;
import com.api.kaleth.security.jwt.JWTProvider;
import com.api.kaleth.security.service.RolService;
import com.api.kaleth.security.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JWTProvider jwtProvider;
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Validated @RequestBody NuevoUsuario nuevousuario, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity("Campos mal puestos",HttpStatus.BAD_REQUEST);		
		}if(usuarioService.existsbyEmail(nuevousuario.getEmail())) {
			return new ResponseEntity("Ese email ya existe",HttpStatus.BAD_REQUEST);
		}if(usuarioService.existsbyNombreUsuario(nuevousuario.getNombreUsuario())) {
			return new ResponseEntity("ese nombre ya existe", HttpStatus.BAD_REQUEST);
		}
		UsUser usuario =
				new UsUser(nuevousuario.getApellido(),nuevousuario.getCedula(),nuevousuario.getDireccion(),
						nuevousuario.getEmail(),nuevousuario.getEstado(),nuevousuario.getFechanacimiento(),
						nuevousuario.getNombre(),nuevousuario.getNombreUsuario(),nuevousuario.getPassword(),nuevousuario.getTelefono());
	
		Set<UsRole> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		
		if(nuevousuario.getRoles().contains("admin")) {
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		}
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		
		return new ResponseEntity("Usuario Guardado",HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<JWTDTO> login (@Validated @RequestBody LoginUsuario loginusuario, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity("Campos mal puestos",HttpStatus.BAD_REQUEST);
			
		}
		Authentication authentication  = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginusuario.getEmail(),loginusuario.getPassword()));
	
	SecurityContextHolder.getContext().setAuthentication(authentication);
	String jwt = jwtProvider.generatorToken(authentication);
	UserDetails userDetails = (UserDetails)authentication.getPrincipal();
	JWTDTO jwtdto= new JWTDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
	
	return new ResponseEntity(jwtdto,HttpStatus.OK);
	}
	
}
