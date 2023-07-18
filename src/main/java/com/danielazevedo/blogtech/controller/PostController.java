package com.danielazevedo.blogtech.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.danielazevedo.blogtech.dto.PostDTO;
import com.danielazevedo.blogtech.dto.response.PostResponseDTO;
import com.danielazevedo.blogtech.model.Post;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.PostRepository;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import com.danielazevedo.blogtech.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostService postService;

    // Cadastrar novo post:
    @GetMapping("/novo")
    public ModelAndView novopost() {

        ModelAndView modelAndView = new ModelAndView("/novapostagem");
        String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario autor = usuarioRepository.findUserByLogin(nomeUsuario);

        modelAndView.addObject("autor", autor);
        modelAndView.addObject("postobj", new PostDTO());

        return modelAndView;
    }

    // Enviando os dados para persistência no banco de dados:
    @PostMapping(value = "/cadastrar", consumes = {"multipart/form-data"})
    public ModelAndView cadastrarPost(@ModelAttribute PostDTO postDTO) throws IOException {

        postService.cadastrarPost(postDTO);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelAndView.addObject("postobj", new PostDTO());
        return modelAndView;

    }

    // Método para renderizar a imagem
    @GetMapping("/imagem/{id}")
    public ResponseEntity<ByteArrayResource> renderizaImagemDoBanco(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post não encontrado!"));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(post.getTipoFile()))
                .body(new ByteArrayResource(post.getImagemPost()));
    }
    
    // Método para renderizar a página de uma publicação específica
    @GetMapping("/{id}")
    public ModelAndView visualizarPost(@PathVariable Long id)  {
    	
    	PostResponseDTO post = postService.buscarPost(id);
    	
    	// Inserir o autor e abrir um campo para comentários!

        ModelAndView modelAndView = new ModelAndView("/post");
        modelAndView.addObject("postobj", post);
        return modelAndView;

    }

}
