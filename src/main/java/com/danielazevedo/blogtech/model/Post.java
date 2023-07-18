package com.danielazevedo.blogtech.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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

	@Column(name = "data_hora_publicacao")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataPublicacao;

	@OneToMany(mappedBy = "post", orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<>();

}