package com.api.kaleth.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.kaleth.domain.UsRole;
import com.api.kaleth.security.enums.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<UsRole, Long>{

	Optional<UsRole> findbyRolNombre (RolNombre rolNombre);
}
