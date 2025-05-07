package com.praticing.tarefas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.praticing.tarefas.entities.Usuario;
import com.praticing.tarefas.enums.UserRole;
import com.praticing.tarefas.repositories.UsuarioRepository;
import com.praticing.tarefas.services.exception.DatabaseException;
import com.praticing.tarefas.services.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Usuario insert(Usuario obj) {
		 obj.setRole(UserRole.ROLE_USER);
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
		public Usuario update(Long id, Usuario obj) {
			try {
			Usuario entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
			} catch (EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);
			}
		}

		private void updateData(Usuario entity, Usuario obj) {
			entity.setNome(obj.getNome());
			entity.setEmail(obj.getEmail());
			entity.setSenha(obj.getSenha());
		}
	}
