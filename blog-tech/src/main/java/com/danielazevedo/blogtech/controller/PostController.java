package com.danielazevedo.blogtech.controller;

import com.danielazevedo.blogtech.dto.PostDTO;
import com.danielazevedo.blogtech.repository.PostRepository;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import com.danielazevedo.blogtech.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        modelAndView.addObject("autores", usuarioRepository.findAll());
        modelAndView.addObject("postobj", new PostDTO());

        return modelAndView;
    }

    // Enviando os dados para persistência no banco de dados:
    @PostMapping("/cadastrar")
    public ModelAndView cadastrarPost(@ModelAttribute PostDTO postDTO) {

        postService.cadastrarPost(postDTO);
        ModelAndView modelAndView = new ModelAndView("/novapostagem");
        modelAndView.addObject("postobj", new PostDTO());

        return modelAndView;
    }

}
