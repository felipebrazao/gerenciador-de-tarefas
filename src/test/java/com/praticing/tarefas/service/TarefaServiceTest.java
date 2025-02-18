package com.praticing.tarefas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.praticing.tarefas.entities.Tarefa;
import com.praticing.tarefas.entities.Usuario;
import com.praticing.tarefas.repositories.TarefaRepository;
import com.praticing.tarefas.repositories.UsuarioRepository;
import com.praticing.tarefas.services.TarefaService;

public class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    private TarefaService tarefaService;

    @BeforeEach
    public void setUp() {
        
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarTarefaSemUsuarioAssociado() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa 1");
        tarefa.setDescricao("Descrição da tarefa 1");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            tarefaService.insert(tarefa);
        });

        assertEquals("Usuário é obrigatório para criar uma tarefa", exception.getMessage());

        verify(tarefaRepository, never()).save(any(Tarefa.class));
    }
    
    @Test
    public void testCriarTarefaComUsuarioAssociado() {
        
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João");
        usuario.setEmail("joao@example.com");
        usuario.setSenha("senhaCodificada");

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa 1");
        tarefa.setDescricao("Descrição da tarefa 1");
        tarefa.setUsuario(usuario);

        // Simula o retorno do usuário ao buscar pelo ID no banco
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(tarefaRepository.save(any(Tarefa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Chama o método testado
        Tarefa tarefaSalva = tarefaService.insert(tarefa);

        // Verificações
        assertNotNull(tarefaSalva);
        assertEquals("Tarefa 1", tarefaSalva.getTitulo());
        assertEquals(usuario, tarefaSalva.getUsuario());

        // Verifica se os métodos foram chamados corretamente
        verify(usuarioRepository).findById(1L);
        verify(tarefaRepository).save(tarefa);
    }
}