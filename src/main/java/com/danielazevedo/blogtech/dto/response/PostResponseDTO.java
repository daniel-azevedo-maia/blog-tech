package com.danielazevedo.blogtech.dto.response;

import com.danielazevedo.blogtech.model.Post;

public record PostResponseDTO(
        Long id,
        String titulo,
        String textoPrincipal) {

    public PostResponseDTO(Post post) {
        this(post.getId(), post.getTitulo(), post.getTextoPrincipal());
    }
}