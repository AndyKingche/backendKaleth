package com.api.kaleth.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.kaleth.domain.UsUser;

@Repository
public interface UsuarioRepository extends JpaRepository<UsUser, Long>{

	Optional<UsUser> findbyNombreUsuario (String nombreUsuario);
	boolean existsbyNombreUsuario(String nombreUsuario);
	boolean existsbyEmail(String email);
}
