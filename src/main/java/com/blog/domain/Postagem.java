package com.blog.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;


@Entity
public class Postagem {

	@Id
	@NotNull
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long id;
	private String titulo;
	private String conteudo;
	@OneToMany
	private List<Tag> tags;
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dataHora;
	
	public Postagem() {
		atualizaHora();
	}

	public Postagem(String titulo, String conteudo, List<Tag> tags) {		
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.tags = tags;
		atualizaHora();
	}
	
	private void atualizaHora() {
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
//		String agora = dtf.format();
//		LocalDateTime date = LocalDateTime.parse(agora, dtf);
		this.dataHora = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long i) {
		this.id = i;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postagem other = (Postagem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Postagem [id=" + id + ", titulo=" + titulo + ", conteudo=" + conteudo + ", tags=" + tags + ", dataHora="
				+ dataHora + "]";
	}
}