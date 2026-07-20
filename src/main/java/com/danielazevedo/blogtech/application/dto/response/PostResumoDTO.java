package com.danielazevedo.blogtech.application.dto.response;

import java.time.LocalDateTime;

public record PostResumoDTO(
        Long id,
        String nomeAutor,
        String titulo,
        String subtitulo,
        String categoria,
        boolean possuiImagem,
        LocalDateTime dataPublicacao
) {
}
