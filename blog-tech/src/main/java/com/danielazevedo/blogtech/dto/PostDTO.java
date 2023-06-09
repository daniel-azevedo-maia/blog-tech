package com.danielazevedo.blogtech.dto;

import com.danielazevedo.blogtech.model.Usuario;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    private Long id;
    private Long usuarioId;
    private String titulo;
    private String subtitulo;
    private String categoria;
    private String textoPrincipal;

}
