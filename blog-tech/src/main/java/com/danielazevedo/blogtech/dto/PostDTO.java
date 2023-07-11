package com.danielazevedo.blogtech.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    private String titulo;
    private String subtitulo;
    private String categoria;
    private String textoPrincipal;
    private MultipartFile imagemPost;
    private LocalDateTime dataPublicacao;

}