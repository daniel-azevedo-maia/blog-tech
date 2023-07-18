package com.danielazevedo.blogtech.dto.response;

import com.danielazevedo.blogtech.model.Post;
import com.danielazevedo.blogtech.model.Usuario;

public record PostResponseDTO(
        Long id,
        Usuario autor,
        String titulo,
        String subtitulo,
        byte[] imagemPost,
        String textoPrincipal) {

	public PostResponseDTO(Post post) {
	    this(post.getId(), post.getUsuario(), post.getTitulo(), post.getSubtitulo(), post.getImagemPost(), post.getTextoPrincipal());
	}

}