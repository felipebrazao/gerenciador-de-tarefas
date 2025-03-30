package com.praticing.tarefas.builder;

import com.praticing.tarefas.entities.Usuario;
import com.praticing.tarefas.enums.UserRole;

public class UsuarioBuilder {
	  private String nome;
	    private String email;
	    private String senha;
	    private UserRole role;

	    public UsuarioBuilder nome(String nome) {
	        this.nome = nome;
	        return this;
	    }

	    public UsuarioBuilder email(String email) {
	        this.email = email;
	        return this;
	    }

	    public UsuarioBuilder senha(String senha) {
	        this.senha = senha;
	        return this;
	    }

	    public UsuarioBuilder role(UserRole role) {
	        this.role = role;
	        return this;
	    }

	    public Usuario build() {
	        return new Usuario(nome, email, senha, role);
	    }
	    
	    public static UsuarioBuilder builder() {
	        return new UsuarioBuilder();
	    }
}
