package com.praticing.tarefas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praticing.tarefas.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	 Optional<Usuario> findByEmail(String email);
}
