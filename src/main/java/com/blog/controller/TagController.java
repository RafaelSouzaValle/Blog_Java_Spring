package com.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.domain.Tag;
import com.blog.repository.TagRepository;

@RestController
public class TagController {

	@Autowired
	TagRepository repository;

	public TagController(TagRepository repository) {
		this.repository = repository;
	}

	@GetMapping(value = "/tags")
	public List<Tag> getTags() {
		return repository.findAll();
	}

	@PostMapping("/tags")
	public Tag addTag(@RequestBody Tag tag) {
		return repository.save(tag);
	}

	@GetMapping(value = "/tag/{id}")
	public Optional<Tag> getTag(@PathVariable Long id) {
		return repository.findById(id);
	}
	
	@GetMapping(value = "/tag/texto/{texto}")
	public List<Tag> getTagByTexto(@PathVariable String texto) {
		List<Tag> tags = getTags();
		List<Tag> encontradas = new ArrayList<>();
		for (Tag tag : tags) {
			if(tag.getTexto().contains(texto)) {
				encontradas.add(tag);
			}
		}
		return encontradas;
	}

	@PutMapping("/tag/{id}")
	public Tag replaceTag(@RequestBody Tag tag, @PathVariable Long id) {

		return repository.findById(id).map(t -> {
			t.setTexto(tag.getTexto());
			return repository.save(t);
		}).orElseGet(() -> {
			tag.setId(id);
			return repository.save(tag);
		});
	}

	@DeleteMapping("/tag/{id}")
	void deleteTag(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
