package com.uninter.backendrest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.uninter.backendrest.model.Tarefa;
import com.uninter.backendrest.repository.TarefaRepository;

@RestController
@RequestMapping({ "/tarefas" })
public class TarefaController {
	private TarefaRepository repository;

	TarefaController(TarefaRepository tarefaRepository) {
		this.repository = tarefaRepository;
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> buscarPorId(@PathVariable long id) {
		var response = repository
			.findById(id)
			.map(registro -> ResponseEntity.ok().body(registro))
			.orElse(ResponseEntity.notFound().build());
		
		return response;
	}
	
	@GetMapping
	public List<?> buscarTodos() {
		return repository.findAll();
	}

	@PostMapping
	public Tarefa criar(@RequestBody Tarefa tarefa) {
		return repository.save(tarefa);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@PathVariable("id") long id, @RequestBody Tarefa tarefaBody) {
		var response = repository
			.findById(id)
			.map(registro -> 
				{
					registro.setNome(tarefaBody.getNome());
					registro.setDataEntrega(tarefaBody.getDataEntrega());
					registro.setResponsavel(tarefaBody.getResponsavel());
					
					Tarefa tarefa = repository.save(registro);
					
					return ResponseEntity.ok().body(tarefa);
				})
			.orElse(ResponseEntity.notFound().build());
		
		return response;
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		var response = repository
			.findById(id)
			.map(registro -> 
				{
					repository.deleteById(id);
					return ResponseEntity.ok().build();
				})
			.orElse(ResponseEntity.notFound().build());
			
		return response;
	}

}