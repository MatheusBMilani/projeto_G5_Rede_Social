package com.projetoG5RedeSocial.controller;

import java.util.List;

import com.projetoG5RedeSocial.model.Tema;
import com.projetoG5RedeSocial.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {

	@Autowired
	private TemaRepository repository;

	@GetMapping //ok
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}") //ok
	public ResponseEntity<Tema> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/temas/{nome}") //ok
	public ResponseEntity<List<Tema>> getByName(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
	}
	
	@PostMapping //ok
	public ResponseEntity<Tema> post(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping //ok
	public ResponseEntity<Tema> put(@RequestBody Tema tema) {
		return ResponseEntity.ok(repository.save(tema));
	}
	
	@DeleteMapping("/{id}") //ok
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
