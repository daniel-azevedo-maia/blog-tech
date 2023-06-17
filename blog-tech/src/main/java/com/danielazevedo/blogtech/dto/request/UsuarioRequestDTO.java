package com.danielazevedo.blogtech.dto.request;

import com.danielazevedo.blogtech.model.Usuario;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    private String nome;
    private String sobrenome;
    private String email;
    private String login;
    private String senha;

}