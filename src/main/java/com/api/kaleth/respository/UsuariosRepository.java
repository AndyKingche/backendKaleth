package com.api.kaleth.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.UsUser;



@RestController
@RequestMapping("/api")
public interface UsuariosRepository extends JpaRepository<UsUser, Long>{
	@Query(value = "SELECT * FROM us_user WHERE email=?", nativeQuery = true)
	Optional<UsUser> findByName(String email);
	
	@Query(value = "SELECT * FROM us_user WHERE email=?", nativeQuery = true)
	List<UsUser> findByEmail(String email);
	
	@Query(value = "SELECT * FROM us_user WHERE token=?", nativeQuery = true)
	List<UsUser> findUserLogged(String token);
	
	@Query(value = "UPDATE us_user SET token=? WHERE id_usuario=?", nativeQuery = true)
	UsUser updateUserLogged(String token,Integer id_usuario);
	
	@Query(value = "UPDATE us_user SET password=?, reset_password=false WHERE email=?", nativeQuery = true)
	UsUser UpdateUserReset(String password,String email);
}
