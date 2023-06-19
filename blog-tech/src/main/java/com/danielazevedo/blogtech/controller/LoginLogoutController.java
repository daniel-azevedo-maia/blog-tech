package com.danielazevedo.blogtech.controller;

import com.danielazevedo.blogtech.dto.PostDTO;
import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/login")
@Controller
public class LoginLogoutController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ModelAndView telaLogin() {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            System.out.println("USUÁRIO JÁ ESTÁ AUTENTICADO!");
            // Usuário já autenticado, redirecionar para a página inicial
            modelAndView.setView(new RedirectView("/"));
        } else {
            System.out.println("USUÁRIO NÃO ESTÁ AUTENTICADO!");
            modelAndView.setViewName("/login");
        }

        return modelAndView;
    }

    @PostMapping
    public ModelAndView handleLogin(@ModelAttribute("username") String username) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = usuarioRepository.findUserByLogin(username);

        if (user != null) {
            modelAndView.addObject("usuarioobj", user);
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.addObject("error", "Nome e/ou senha inválido(s)!");
            modelAndView.setViewName("/login");
        }

        return modelAndView;
    }

    @GetMapping("**/logout")
    public String fazerLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login";
    }

}
