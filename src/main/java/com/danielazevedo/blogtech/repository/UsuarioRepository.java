package com.danielazevedo.blogtech.repository;


import com.danielazevedo.blogtech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.login = ?1")
    Usuario findUserByLogin(String login);

    @Query("SELECT u.nome FROM Usuario u WHERE u.login = ?1")
    String nomeUser(String login);

    boolean existsByLogin(String login);

}
