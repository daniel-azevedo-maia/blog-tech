package com.danielazevedo.blogtech.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementacaoUserDetailsService implementacaoUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario/novo").permitAll()
                .antMatchers(HttpMethod.POST, "/usuario/cadastrar").permitAll()
                //.antMatchers(HttpMethod.GET, "/novapostagem").hasAnyRole("ADMIN", "SIMPLEUSER")
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .and()
                .logout().logoutSuccessUrl("/login")
                // Mapeia URL de Logout e invalida usuário autenticado
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    /*
    @Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        // Desabilita o mecanismo de proteção CSRF (Cross-Site Request Forgery), que ajuda a prevenir
        // ataques onde o usuário é enganado a realizar ações indesejadas em uma aplicação web na qual
        // está autenticado.

    .authorizeRequests()
        // Essa chamada ao método retorna uma instância de uma classe auxiliar usada para
        // controlar quais requisições HTTP devem ser protegidas por segurança.

    .antMatchers(HttpMethod.GET, "/").permitAll()
        // Especifica que todas as requisições GET para a URL raiz ("/") são permitidas para
        // todos os usuários, mesmo aqueles que não estão autenticados.

    .antMatchers(HttpMethod.GET, "/novapostagem").hasAnyRole("ADMIN")
        // Especifica que todas as requisições GET para a URL "/novapostagem" são permitidas apenas
        // para usuários autenticados que possuem a role de "ADMIN".

    .anyRequest().authenticated()
        // Especifica que todas as outras requisições devem ser autenticadas.
        // Se a autenticação não for provida, o Spring Security irá retornar uma resposta de
        // erro HTTP 401 Unauthorized.

    .and().formLogin().permitAll()
        // Permite a todos os usuários autenticar através de um formulário de login padrão.
        // O Spring Security irá criar e controlar um formulário de login se você não especificar um.

    .loginPage("/login")
        // Personaliza a URL da página de login. Se não especificado, o Spring Security fornece um
        // formulário de login padrão acessado através da URL "/login".

    .defaultSuccessURL("/", true)
        // Personaliza a URL para a qual o usuário será redirecionado após autenticação bem-sucedida.
        // O segundo parâmetro 'true' força o redirecionamento sempre para esta URL.

    .failureUrl("/login?error=true")
        // Personaliza a URL para a qual o usuário será redirecionado após falha na autenticação.

    .and()
    .logout().logoutSuccessUrl("/login")
        // Personaliza a URL para a qual o usuário será redirecionado após realizar o logout.

    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        // Configura a URL que irá realizar o logout do usuário. Uma vez que esta URL é acessada
        // via GET, POST, etc., o usuário será deslogado e redirecionado para a URL especificada em logoutSuccessUrl().
}

     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(implementacaoUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/**/favicon.ico"
        );
    }

}
