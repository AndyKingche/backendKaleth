package com.api.kaleth.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.UsUser;
import com.api.kaleth.respository.UsuariosRepository;

@RestController
@CrossOrigin(origins = "/**", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/api")
public class UsuariosController {
	@Autowired
	UsuariosRepository UsUserrepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/user")
	public List<UsUser> getUsers(){
		
		List<UsUser> user = UsUserrepository.findAll();
		
		return user;
				}
	
	@GetMapping("/user/{id}")
	public Optional<UsUser> getUser(@PathVariable Long id) throws ResourceNotFoundException{
		Optional<UsUser> user = UsUserrepository.findById(id);
		
		return user;
	}
	
	@PostMapping("/user")
	public UsUser createUser(@RequestBody UsUser user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return UsUserrepository.save(user);
	}
	
	@PostMapping("/user/client/register")
	public UsUser createClient(@RequestBody UsUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return UsUserrepository.save(user);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<String> updateUser(@RequestBody UsUser user, @PathVariable Long id)
		throws ResourceNotFoundException{
		
		UsUser findUser = UsUserrepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encuentra el id"));
	
		findUser.setNombre(user.getNombre());
		findUser.setApellido(user.getApellido());
		findUser.setCedula(user.getCedula());
		findUser.setTelefono(user.getTelefono());
		findUser.setEmail(user.getEmail());
		findUser.setPassword(passwordEncoder.encode(user.getPassword()));
		findUser.setEstado(user.getEstado());
		findUser.setFechanacimiento(user.getFechanacimiento());
		findUser.setDireccion(user.getDireccion());
		findUser.setGenero(user.getGenero());
		findUser.setEstadocivil(user.getEstadocivil());
		findUser.setToken(user.getToken());
		findUser.setRol(user.getRol());
		findUser.setResetPassword(user.getResetPassword());
		
		UsUser updateUser = UsUserrepository.save(findUser);
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Usuario se ha actualizado correctamente " + "" + "\"}");
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteuser(@PathVariable Long id)throws ResourceNotFoundException{
		
		UsUser findUser = UsUserrepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontro el id"));
		UsUserrepository.deleteById(id);
		
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body("{\"mensaje\": \"El Usuario se ha eliminado correctamente " + id + "\"}");
	}
	
	@RequestMapping(value="/user/findemail/{email}",produces = {"application/json"},method= RequestMethod.GET)
	public List<UsUser> findUserbyEmail(@PathVariable("email") String email) {
			
		try {
			List<UsUser> user = UsUserrepository.findByEmail(email);
			 System.out.println(user);
			 
			 return user;
		} catch (Exception e) {
			System.out.println("*************************ERRROR AL CONSULTAR"+e);
		}
		return null;
	}
	
	@RequestMapping(value="/user/finduserlogged/{token}",produces = {"application/json"},method= RequestMethod.GET)
	public List<UsUser> findUserLogin(@PathVariable("token") String token) {
			
		try {
			List<UsUser> user = UsUserrepository.findUserLogged(token);
			 System.out.println(user);
			 
			 return user;
		} catch (Exception e) {
			System.out.println("*************************ERRROR AL CONSULTAR"+e);
		}
		return null;
	}
	
	@RequestMapping(value="/user/updateuserlogged/{token}/{id_usuario}",produces = {"application/json"},method= RequestMethod.GET)
	public Boolean updateUserLogin(@PathVariable("token") String token,@PathVariable("id_usuario") Integer id_usuario) {
			
		try {
			UsUser user = UsUserrepository.updateUserLogged(token, id_usuario);
			 System.out.println(user);
			 
			 return true;
		} catch (Exception e) {
			System.out.println("*************************ERRROR AL CONSULTAR"+e);
			return false;
		}
		
	}
	@RequestMapping(value="/user/resetuserpassword/{password}/{email}",produces = {"application/json"},method= RequestMethod.GET)
	public Boolean updateUserReset(@PathVariable("password") String password,@PathVariable("email") String email) {
			
		try {
			System.out.print("password"+password);
			System.out.print("user"+email);
			
			UsUser user = UsUserrepository.UpdateUserReset(passwordEncoder.encode(password), email);
			 System.out.println(user);
			 
			 return true;
		} catch (Exception e) {
			System.out.println("*************************ERRROR AL CONSULTAR"+e);
			return false;
		}
		
	}
	
}
