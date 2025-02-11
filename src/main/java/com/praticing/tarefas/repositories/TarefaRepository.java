package com.praticing.tarefas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praticing.tarefas.entities.Tarefa;
import com.praticing.tarefas.entities.Usuario;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	List<Tarefa> findByUsuario(Usuario usuario);
}
