package com.danielazevedo.blogtech.controller;

import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.PostRepository;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Renderiza as publicações na página inicial (feed de publicações)
    @GetMapping
    public ModelAndView inicio(Principal principal) {

        // OBS.:
        // adicionar ao código verificação para ver se a sessão atual do usuário ainda é válida
        // Um usuário pode ser autenticado mas não ter uma sessão válida, e vice-versa.

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
