package com.danielazevedo.blogtech.repository;

import com.danielazevedo.blogtech.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
