package com.api.kaleth.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.kaleth.domain.UsRoleUser;
import com.api.kaleth.domain.UsRoleUserPk;


@RestController
@RequestMapping("/api")
public interface RoleUserRepository extends JpaRepository<UsRoleUser, Long>{
	@Query(value = "SELECT * FROM us_rolesxu WHERE id_usuario=10", nativeQuery = true)
	List<UsRoleUser> findByIdUsuario(Integer id);

}
