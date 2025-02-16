package com.praticing.tarefas.resources;

import java.util.List;

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

import com.praticing.tarefas.entities.Tarefa;
import com.praticing.tarefas.services.TarefaService;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "http://localhost:3000")
public class TarefaResource {

    @Autowired
    private TarefaService service;

    @GetMapping
    public ResponseEntity<List<Tarefa>> findAll() {
        List<Tarefa> tarefas = service.findAll();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable Long id) {
        Tarefa tarefa = service.findById(id);
        return ResponseEntity.ok(tarefa);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Tarefa tarefa) {
        try {
            Tarefa novaTarefa = service.insert(tarefa);
            return ResponseEntity.ok(novaTarefa);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        Tarefa tarefaAtualizada = service.update(id, tarefa);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}