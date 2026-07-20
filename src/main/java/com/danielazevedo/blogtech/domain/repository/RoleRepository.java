package com.danielazevedo.blogtech.domain.repository;

import com.danielazevedo.blogtech.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByNomeRole(String nomeRole);
}
