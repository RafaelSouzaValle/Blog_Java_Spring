package com.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.domain.Postagem;
import com.blog.domain.Tag;
import com.blog.repository.PostagemRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/")
public class PostagemController {

	@Autowired
	PostagemRepository repository;

	public PostagemController(PostagemRepository repository) {
		this.repository = repository;
	}

	@GetMapping(value = "/postagens")
	public List<Postagem> getPostagens() {
		return repository.findAll();
	}

	@PostMapping("/postagens")
	Postagem addPostagem(@RequestBody Postagem postagem) {
		return repository.save(postagem);
	}

	@GetMapping(value = "/postagem/{id}")
	public Optional<Postagem> getPostagem(@PathVariable Long id) {
		return repository.findById(id);
	}
	
	@GetMapping(value = "/postagem/titulo/{titulo}")
	public List<Postagem> getPostagemByTitulo(@PathVariable String titulo) {
		List<Postagem> postagens = getPostagens();
		List<Postagem> encontradas = new ArrayList<>();
		for (Postagem postagem : postagens) {
			if(postagem.getTitulo().contains(titulo)) {
				encontradas.add(postagem);
			}
		}
		return encontradas;
	}
	
	@GetMapping(value = "/postagem/tag/{tag}")
	public List<Postagem> getPostagemByTag(@PathVariable String tag) {
		List<Postagem> postagens = getPostagens();
		List<Postagem> encontradas = new ArrayList<>();
		for (Postagem postagem : postagens) {
			for (Tag t : postagem.getTags()) {
				if(t.getTexto().contains(tag)) {
					encontradas.add(postagem);
					break;
				}
			}
		}
		return encontradas;
	}

	@PutMapping("/postagem/{id}")
	public Postagem replacePostagem(@RequestBody Postagem postagem, @PathVariable Long id) {

		return repository.findById(id).map(employee -> {
			employee.setTitulo(postagem.getTitulo());
			employee.setConteudo(postagem.getConteudo());
			employee.setTags(postagem.getTags());
			return repository.save(employee);
		}).orElseGet(() -> {
			postagem.setId(id);
			return repository.save(postagem);
		});
	}

	@DeleteMapping("/postagem/{id}")
	void deletePostagem(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
