package com.praticing.tarefas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praticing.tarefas.entities.Usuario;
import com.praticing.tarefas.enums.UserRole;
import com.praticing.tarefas.records.AuthenticationDTO;
import com.praticing.tarefas.records.LoginResponseDTO;
import com.praticing.tarefas.records.RegisterDTO;
import com.praticing.tarefas.repositories.UsuarioRepository;
import com.praticing.tarefas.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository repository;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((Usuario)auth.getPrincipal());

		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
	    if (this.repository.findByEmail(data.login()) != null) {
	        return ResponseEntity.badRequest().build();
	    }

	    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
	    
	    
	    Usuario newUser = new Usuario();
	    newUser.setEmail(data.login()); 
	    newUser.setSenha(encryptedPassword);
	    newUser.setRole(UserRole.ROLE_USER); 
	    
	    this.repository.save(newUser);
	    
	    return ResponseEntity.ok().build();
	}
}
