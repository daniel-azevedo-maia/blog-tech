package com.danielazevedo.blogtech.domain.repository;

import com.danielazevedo.blogtech.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByDataPublicacaoDesc();
}
