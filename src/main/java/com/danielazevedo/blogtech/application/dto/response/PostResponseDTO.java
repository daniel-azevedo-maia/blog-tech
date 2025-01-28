package com.danielazevedo.blogtech.application.dto.response;

import com.danielazevedo.blogtech.domain.model.Post;
import com.danielazevedo.blogtech.domain.model.Usuario;

import java.time.LocalDateTime;

public record PostResponseDTO(
		Long id,
		Usuario autor,
		String titulo,
		String subtitulo,
		byte[] imagemPost,
		String textoPrincipal,
		LocalDateTime dataPublicacao) {

	public PostResponseDTO(Post post) {
		this(post.getId(),
				post.getUsuario(),
				post.getTitulo(),
				post.getSubtitulo(),
				post.getImagemPost(),
				post.getTextoPrincipal(),
				post.getDataPublicacao());
	}
}
