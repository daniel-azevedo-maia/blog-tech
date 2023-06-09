package com.danielazevedo.blogtech.service;

import com.danielazevedo.blogtech.controller.dto.UsuarioDTO;
import com.danielazevedo.blogtech.model.Post;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cadastrar usuário:

    public Usuario cadastrarUsuario(UsuarioDTO usuarioDTO) {

        // Criar um USUARIO a partir do DTO:

        Usuario usuario = new Usuario();

        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setSobrenome(usuarioDTO.getSobrenome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(usuarioDTO.getSenha());

        return usuarioRepository.save(usuario);


    }

}
