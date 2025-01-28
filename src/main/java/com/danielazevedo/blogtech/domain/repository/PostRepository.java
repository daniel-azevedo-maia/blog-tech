package com.danielazevedo.blogtech.domain.repository;

import com.danielazevedo.blogtech.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.usuario.id = :usuarioId ORDER BY p.dataPublicacao DESC")
    List<Post> findByUsuarioIdOrderByDataPublicacao(Long usuarioId);

    @Query("SELECT p FROM Post p ORDER BY p.dataPublicacao DESC")
    List<Post> findAllOrderByDataPublicacao();
}
