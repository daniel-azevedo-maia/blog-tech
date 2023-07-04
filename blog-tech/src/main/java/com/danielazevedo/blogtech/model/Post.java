package com.danielazevedo.blogtech.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String subtitulo;

    @Column(nullable = false)
    private String categoria;

    @Lob
    private byte[] imagemPost;

    private String nomeFile;
    private String tipoFile;

    @Lob
    @Column(name = "textoPrincipal", nullable = false)
    private String textoPrincipal;

    @Column(name = "data_publicacao")
    private Timestamp dataPublicacao;

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = Timestamp.valueOf(dataPublicacao);
    }

}