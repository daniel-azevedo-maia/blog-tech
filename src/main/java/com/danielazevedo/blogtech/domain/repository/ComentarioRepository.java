package com.danielazevedo.blogtech.domain.repository;

import com.danielazevedo.blogtech.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    @Query("SELECT c FROM Comentario c WHERE c.post.id = :postId ORDER BY c.dataPublicacao ASC")
    List<Comentario> findByPostIdOrderByDataPublicacao(Long postId);

    @Query("SELECT c FROM Comentario c WHERE c.autor.id = :autorId ORDER BY c.dataPublicacao ASC")
    List<Comentario> findByAutorIdOrderByDataPublicacao(Long autorId);
}
