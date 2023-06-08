package com.danielazevedo.blogtech.service;

import com.danielazevedo.blogtech.model.Post;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.PostRepository;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cadastrar post:

    public Post cadastrarPost(Post post) {

        long autorId = post.getUsuario().getId();
        Usuario autor = usuarioRepository.findById(autorId).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        post.setUsuario(autor);
        return postRepository.save(post);

    }

}
