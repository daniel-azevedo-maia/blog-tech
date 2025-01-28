package com.danielazevedo.blogtech.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comentario")
public class Comentario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@NotBlank(message = "O texto do comentário é obrigatório.")
	@Lob
	@Column(name = "texto_comentario", nullable = false)
	private String textoComentario;

	@NotNull(message = "A data de publicação é obrigatória.")
	@Column(name = "data_hora_comentario", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataPublicacao;

	@NotNull(message = "O autor do comentário é obrigatório.")
	@ManyToOne
	@JoinColumn(name = "autor_id", nullable = false)
	private Usuario autor;

	@NotNull(message = "O post relacionado é obrigatório.")
	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
}