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
}
