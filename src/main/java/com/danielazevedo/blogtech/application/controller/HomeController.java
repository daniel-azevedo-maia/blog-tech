package com.danielazevedo.blogtech.application.controller;

import com.danielazevedo.blogtech.application.service.PostService;
import com.danielazevedo.blogtech.application.service.UsuarioService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final PostService postService;
    private final UsuarioService usuarioService;

    public HomeController(PostService postService, UsuarioService usuarioService) {
        this.postService = postService;
        this.usuarioService = usuarioService;
    }

    @GetMapping({"/", "/home", "/index.html"})
    public ModelAndView inicio(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("index");
        boolean autenticado = authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);

        modelAndView.addObject("autenticado", autenticado);
        modelAndView.addObject("postagens", postService.listarTodosPosts());
        if (autenticado) {
            modelAndView.addObject("nome", usuarioService.buscarNomePorLogin(authentication.getName()));
        }
        return modelAndView;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
