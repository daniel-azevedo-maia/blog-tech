package com.danielazevedo.blogtech.domain.repository;

import com.danielazevedo.blogtech.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.login = :login")
    Optional<Usuario> findUserByLogin(String login);

    @Query("SELECT u.nome FROM Usuario u WHERE u.login = :login")
    Optional<String> findNomeByLogin(String login);

    boolean existsByLogin(String login);


}
