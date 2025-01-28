package com.danielazevedo.blogtech.application.service;

import com.danielazevedo.blogtech.application.dto.PostDTO;
import com.danielazevedo.blogtech.application.dto.response.PostResponseDTO;
import com.danielazevedo.blogtech.domain.model.Post;
import com.danielazevedo.blogtech.domain.model.Usuario;
import com.danielazevedo.blogtech.domain.repository.PostRepository;
import com.danielazevedo.blogtech.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Post cadastrarPost(PostDTO postDTO) throws IOException {
        Usuario autor = obterUsuarioAutenticado();

        Post post = new Post();
        post.setUsuario(autor);
        post.setTitulo(postDTO.getTitulo());
        post.setSubtitulo(postDTO.getSubtitulo());
        post.setCategoria(postDTO.getCategoria());
        post.setTextoPrincipal(postDTO.getTextoPrincipal());
        post.setDataPublicacao(LocalDateTime.now());
        post.setImagemPost(postDTO.getImagemPost().getBytes());
        post.setNomeFile(postDTO.getImagemPost().getOriginalFilename());
        post.setTipoFile(postDTO.getImagemPost().getContentType());

        return postRepository.save(post);
    }

    public PostResponseDTO buscarPostPorId(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado!"));
        return converterParaResponseDTO(post);
    }

    public List<PostResponseDTO> listarTodosPosts() {
        return postRepository.findAll().stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ByteArrayResource> obterImagemDoPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado!"));

        if (post.getImagemPost() == null || post.getTipoFile() == null) {
            throw new IllegalArgumentException("Imagem não disponível para este post!");
        }

        ByteArrayResource resource = new ByteArrayResource(post.getImagemPost());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(post.getTipoFile()))
                .body(resource);
    }

    private PostResponseDTO converterParaResponseDTO(Post post) {
        return new PostResponseDTO(
                post.getId(),
                post.getUsuario(),
                post.getTitulo(),
                post.getSubtitulo(),
                post.getImagemPost(),
                post.getTextoPrincipal(),
                post.getDataPublicacao()
        );
    }

    private Usuario obterUsuarioAutenticado() {
        String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findUserByLogin(nomeUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário autenticado não encontrado!"));
    }
}
