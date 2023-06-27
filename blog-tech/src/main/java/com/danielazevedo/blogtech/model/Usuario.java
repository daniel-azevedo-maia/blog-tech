package com.danielazevedo.blogtech.model;

import com.danielazevedo.blogtech.dto.request.UsuarioRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    // Conversão do DTO:
    public Usuario(UsuarioRequestDTO usuarioRequestDTO) {
        this.nome = usuarioRequestDTO.getNome();
        this.sobrenome = usuarioRequestDTO.getSobrenome();
        this.email = usuarioRequestDTO.getEmail();
        this.login = usuarioRequestDTO.getLogin();
        this.senha = usuarioRequestDTO.getSenha();
        this.endereco = usuarioRequestDTO.getEndereco();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private String email;

    @Embedded
    // Deletando um objeto Usuario, todos os endereços serão deletados em cascata.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Post> postagens = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_role", // tabela de ligação
            joinColumns = @JoinColumn(
                    name = "usuario_id", // coluna da tabela de ligação referente à tabela "usuario"
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", // coluna da tabela de ligação referente à tabela "role"
                    referencedColumnName = "id"
            )
    )
    private List<Role> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}