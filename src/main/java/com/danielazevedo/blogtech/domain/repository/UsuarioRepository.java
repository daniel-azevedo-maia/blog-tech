package com.danielazevedo.blogtech.domain.repository;

import com.danielazevedo.blogtech.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLoginIgnoreCase(String login);

    boolean existsByLoginIgnoreCase(String login);

    boolean existsByEmailIgnoreCase(String email);

    List<Usuario> findAllByOrderByNomeAscSobrenomeAsc();
}
