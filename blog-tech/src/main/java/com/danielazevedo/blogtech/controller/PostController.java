package com.danielazevedo.blogtech.controller;

import com.danielazevedo.blogtech.model.Post;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.PostRepository;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import com.danielazevedo.blogtech.service.PostService;
import com.danielazevedo.blogtech.service.UsuarioService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostService postService;

    // Cadastrar novo post:
    @GetMapping("/post/novo")
    public ModelAndView novopost() {
        ModelAndView modelAndView = new ModelAndView("/novapostagem");

        modelAndView.addObject("autores", usuarioRepository.findAll());

        modelAndView.addObject("postobj", new Post());

        return modelAndView;
    }

    @PostMapping("/post/cadastrar")
    public ModelAndView cadastrarPost(Post inputPost) {
        postService.cadastrarPost(inputPost);
        ModelAndView modelAndView = new ModelAndView("/novapostagem");
        modelAndView.addObject("postobj", new Post());
        return modelAndView;
    }

}
