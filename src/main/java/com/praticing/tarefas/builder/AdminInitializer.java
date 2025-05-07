package com.praticing.tarefas.builder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.praticing.tarefas.entities.Usuario;
import com.praticing.tarefas.enums.UserRole;
import com.praticing.tarefas.repositories.UsuarioRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByEmail("admin@admin.com") == null) {
            Usuario admin = new Usuario();
            admin.setNome("Admin");
            admin.setEmail("admin@admin.com");
            admin.setSenha(passwordEncoder.encode("komortal0"));
            admin.setRole(UserRole.ROLE_ADMIN);
            
            usuarioRepository.save(admin);
            System.out.println("Usuário admin criado com sucesso!");
        }
    }
}