package com.danielazevedo.blogtech.application.controller;

import com.danielazevedo.blogtech.application.service.PostService;

import com.danielazevedo.blogtech.application.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final PostService postService;

    private final UsuarioService usuarioService;

    @GetMapping
    public ModelAndView inicio(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("index");

        if (principal != null) {
            String nomeUsuario = usuarioService.buscarNomePorLogin(principal.getName());
            modelAndView.addObject("nome", nomeUsuario);
            modelAndView.addObject("autenticado", true);
            modelAndView.addObject("postagens", postService.listarTodosPosts());
        } else {
            modelAndView.addObject("autenticado", false);
        }

        return modelAndView;
    }

    @GetMapping("/index.html")
    public String index() {
        return "index";
    }

}
