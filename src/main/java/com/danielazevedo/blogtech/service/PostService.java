package com.danielazevedo.blogtech.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.danielazevedo.blogtech.dto.PostDTO;
import com.danielazevedo.blogtech.dto.response.PostResponseDTO;
import com.danielazevedo.blogtech.model.Post;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.PostRepository;
import com.danielazevedo.blogtech.repository.UsuarioRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cadastrar publicação:

    public Post cadastrarPost(PostDTO postDTO) throws IOException {

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
        post.setImagemPost(postDTO.getImagemPost().getBytes());
        post.setNomeFile(postDTO.getImagemPost().getName());
        post.setTipoFile(postDTO.getImagemPost().getContentType());

        return postRepository.save(post);

    }
    
    public PostResponseDTO buscarPost(Long id) {
    	
    	Post post = postRepository.findById(id).orElse(new Post());
    	
    	// Converter para PostResponseDTO:
    	PostResponseDTO postResponseDTO = new PostResponseDTO(
    			post.getId(),
    			post.getUsuario(),
    			post.getTitulo(),
    			post.getSubtitulo(),
    			post.getImagemPost(),
    			post.getTextoPrincipal()
    			);
    	
    	return postResponseDTO;
    	
    	
    }

}
