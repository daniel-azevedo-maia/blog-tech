package com.danielazevedo.blogtech.domain.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 180)
    private String titulo;

    @Column(nullable = false, length = 300)
    private String subtitulo;

    @Column(nullable = false, length = 100)
    private String categoria;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "imagem_post")
    private byte[] imagemPost;

    @Column(name = "nome_file")
    private String nomeFile;

    @Column(name = "tipo_file", length = 100)
    private String tipoFile;

    @Lob
    @Column(name = "textoPrincipal", nullable = false)
    private String textoPrincipal;

    @Column(name = "data_hora_publicacao", nullable = false)
    private LocalDateTime dataPublicacao;

    public Post() {
    }

    public Post(Usuario usuario, String titulo, String subtitulo, String categoria,
                String textoPrincipal, LocalDateTime dataPublicacao) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.categoria = categoria;
        this.textoPrincipal = textoPrincipal;
        this.dataPublicacao = dataPublicacao;
    }

    public void definirImagem(byte[] conteudo, String nomeArquivo, String tipoArquivo) {
        this.imagemPost = conteudo;
        this.nomeFile = nomeArquivo;
        this.tipoFile = tipoArquivo;
    }

    public boolean possuiImagem() {
        return imagemPost != null && imagemPost.length > 0 && tipoFile != null;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public byte[] getImagemPost() {
        return imagemPost;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public String getTipoFile() {
        return tipoFile;
    }

    public String getTextoPrincipal() {
        return textoPrincipal;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }
}
