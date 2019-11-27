package com.hias.apps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hias.apps.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query(
	     value = "SELECT * FROM role  where role_name = :roleName", 
	     nativeQuery=true
	)
	public Role findIdByRoleName(@Param("roleName") String roleName);
}
