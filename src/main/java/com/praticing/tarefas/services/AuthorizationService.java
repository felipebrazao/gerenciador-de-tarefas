package com.praticing.tarefas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.praticing.tarefas.repositories.UsuarioRepository;


@Service
public class AuthorizationService implements UserDetailsService {

	@Autowired
	 UsuarioRepository repository;

	   @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        return repository.findByEmail(email);
	    }
}