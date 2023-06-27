package com.danielazevedo.blogtech.service;

import com.danielazevedo.blogtech.dto.PostDTO;
import com.danielazevedo.blogtech.model.Post;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.PostRepository;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cadastrar publicação:

    public Post cadastrarPost(PostDTO postDTO) {

        String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = usuarioRepository.findUserByLogin(nomeUsuario);

        long autorId = usuario.getId();

        Usuario autor = usuarioRepository.findById(autorId).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // Criar um POST a partir do DTO:
        Post post = new Post();

        post.setUsuario(autor);
        post.setTitulo(postDTO.getTitulo());
        post.setSubtitulo(postDTO.getSubtitulo());
        post.setCategoria(postDTO.getCategoria());
        post.setTextoPrincipal(postDTO.getTextoPrincipal());
        post.setDataPublicacao(LocalDateTime.now());

        return postRepository.save(post);

    }

}
