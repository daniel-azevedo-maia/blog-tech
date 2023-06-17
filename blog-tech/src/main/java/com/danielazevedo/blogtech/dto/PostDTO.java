package com.danielazevedo.blogtech.dto;

import lombok.Data;

@Data
public class PostDTO {

    private Long id;
    private Long usuarioId;
    private String titulo;
    private String subtitulo;
    private String categoria;
    private String textoPrincipal;

}