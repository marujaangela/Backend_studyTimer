package com.marujaangela.webtech_project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testklasse für {@link TodoController}.
 * Verwendet MockMvc und Mockito zur Isolierung von Controller-Logik.
 */
@WebMvcTest(TodoController.class)
public class TodoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoRepository todoRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Todo todo;

    /**
     * Vor jedem Test ein Beispiel-Todo vorbereiten.
     */
    @BeforeEach
    void setUp() {
        todo = new Todo();
        todo.setId(1L);
        todo.setTaskDescription("Test-Todo");
        todo.setCompleted(false);
    }

    /**
     * Testet die Erstellung eines neuen Todos via POST /api/todos.
     */
    @Test
    void testCreateTodo() throws Exception {
        when(todoRepository.save(ArgumentMatchers.any(Todo.class))).thenReturn(todo);

        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskDescription").value("Test-Todo"));
    }

    /**
     * Testet das Abrufen aller Todos via GET /api/todo.
     */
    @Test
    void testGetTodos() throws Exception {
        when(todoRepository.findAll()).thenReturn(Arrays.asList(todo));

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taskDescription").value("Test-Todo"));
    }

    /**
     * Testet das Löschen eines Todos via DELETE /api/todos/{id}.
     */
    @Test
    void testDeleteTodo() throws Exception {
        when(todoRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/todos/1"))
                .andExpect(status().isNoContent());


        verify(todoRepository, times(1)).deleteById(1L);
    }

    /**
     * Testet das Aktualisieren eines existierenden Todos via PUT /api/todos/{id}.
     */
    @Test
    void testUpdateTodo() throws Exception {
        Todo updated = new Todo();
        updated.setId(1L);
        updated.setTaskDescription("Geändert");
        updated.setCompleted(true);

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        when(todoRepository.save(any(Todo.class))).thenReturn(updated);

        mockMvc.perform(put("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskDescription").value("Geändert"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    /**
     * Testet das Fehlverhalten beim Aktualisieren eines nicht existierenden Todos.
     */
    @Test
    void testUpdateNonexistentTodo() throws Exception {
        when(todoRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/todos/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo)))
                .andExpect(status().isNotFound());
    }
}
