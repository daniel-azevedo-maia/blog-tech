package com.danielazevedo.blogtech.security;

import com.danielazevedo.blogtech.model.Usuario;
import com.danielazevedo.blogtech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImplementacaoUserDetailsService implements UserDetailsService {

    // A classe Usuario implementa UserDetails. Isso significa que Usuario pode ser retornado
    // em um método em que UserDetails é o retorno.

    // Esta classe atual implementa UserDetailsService!
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findUserByLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não foi encontrado!");
        }

        return usuario;
    }
}