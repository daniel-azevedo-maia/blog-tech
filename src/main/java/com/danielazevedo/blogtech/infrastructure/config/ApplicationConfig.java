package com.danielazevedo.blogtech.infrastructure.config;

import com.danielazevedo.blogtech.domain.model.Role;
import com.danielazevedo.blogtech.domain.repository.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ApplicationConfig {

    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    ApplicationRunner inicializarDadosEssenciais(RoleRepository roleRepository) {
        return arguments -> roleRepository.findByNomeRole(Role.ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Role(Role.ROLE_USER)));
    }
}
