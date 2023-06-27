package com.danielazevedo.blogtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EntityScan(basePackages = "com.danielazevedo.blogtech.model")
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = "com.danielazevedo.blogtech.repository")
@EnableTransactionManagement
@EnableWebMvc
public class BlogTechApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BlogTechApplication.class, args);
	}

	// Estamos habilitando um configurador, dizendo q ele vai interceptar o login
	// e mandar pra nossa tela de login.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);

	}

}

/*
	Utilitário para criptografar:

	public static void main(String[] args) {
        String senha = "admin"; // Senha que deseja criptografar

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = passwordEncoder.encode(senha);

        System.out.println("Senha original: " + senha);
        System.out.println("Senha criptografada: " + senhaCriptografada);
    }
* */