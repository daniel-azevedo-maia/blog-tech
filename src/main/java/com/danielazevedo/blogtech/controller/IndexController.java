package com.danielazevedo.blogtech.controller;


import com.danielazevedo.blogtech.repository.PostRepository;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ModelAndView inicio(Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String sessionId = details.getSessionId();

        ModelAndView modelAndView = new ModelAndView("index");

        if (principal != null) {
            // Recuperar dados do BD:
            String nome = usuarioRepository.nomeUser(authentication.getName());
            modelAndView.addObject("nome", nome);
            modelAndView.addObject("autenticado", true);
            modelAndView.addObject("postagens", postRepository.findAll());
        } else {
            modelAndView.addObject("autenticado", false);
        }

        return modelAndView;
    }

}
