package com.praticing.tarefas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.praticing.tarefas.entities.Tarefa;
import com.praticing.tarefas.repositories.TarefaRepository;
import com.praticing.tarefas.services.exception.DatabaseException;
import com.praticing.tarefas.services.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

   
    public List<Tarefa> findAll() {
        return repository.findAll();
    }

  
    public Tarefa findById(Long id) {
        Optional<Tarefa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

   
    public Tarefa insert(Tarefa obj) {
        return repository.save(obj);
    }

   
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
     public Tarefa update(Long id, Tarefa obj) {
        try {
            Tarefa entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    
    private void updateData(Tarefa entity, Tarefa obj) {
        entity.setTitulo(obj.getTitulo());
        entity.setDescricao(obj.getDescricao());
        entity.setDataVencimento(obj.getDataVencimento());
        entity.setStatus(obj.getStatus());
    }
}