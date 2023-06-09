package com.danielazevedo.blogtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EntityScan(basePackages = "com.danielazevedo.blogtech.model")
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = "com.danielazevedo.blogtech.repository")
@EnableTransactionManagement
public class BlogTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogTechApplication.class, args);
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