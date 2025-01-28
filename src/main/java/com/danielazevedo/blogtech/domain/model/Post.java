package com.danielazevedo.blogtech.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@NotNull(message = "O autor do post é obrigatório.")
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@NotBlank(message = "O título é obrigatório.")
	@Column(nullable = false)
	private String titulo;

	@NotBlank(message = "O subtítulo é obrigatório.")
	@Column(nullable = false)
	private String subtitulo;

	@NotBlank(message = "A categoria é obrigatória.")
	@Column(nullable = false)
	private String categoria;

	@Lob
	private byte[] imagemPost;

	private String nomeFile;

	private String tipoFile;

	@NotBlank(message = "O texto principal é obrigatório.")
	@Lob
	@Column(name = "textoPrincipal", nullable = false)
	private String textoPrincipal;

	@NotNull(message = "A data de publicação é obrigatória.")
	@Column(name = "data_hora_publicacao", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataPublicacao;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comentario> comentarios = new ArrayList<>();

	public void adicionarComentario(Comentario comentario) {
		comentario.setPost(this);
		comentarios.add(comentario);
	}

	public void removerComentario(Comentario comentario) {
		comentarios.remove(comentario);
	}
}