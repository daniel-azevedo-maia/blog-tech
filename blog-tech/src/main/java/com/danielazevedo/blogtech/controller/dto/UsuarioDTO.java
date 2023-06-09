package com.danielazevedo.blogtech.controller.dto;

import com.danielazevedo.blogtech.model.Usuario;
import lombok.Data;

import javax.persistence.Column;

@Data
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String login;
    private String senha;

}
