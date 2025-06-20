package com.marujaangela.webtech_project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://frontend-studytimer.onrender.com")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo saved = repository.save(todo);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        Optional<Todo> optionalTodo = repository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setTaskDescription(updatedTodo.getTaskDescription());
            todo.setCompleted(updatedTodo.isCompleted());
            Todo saved = repository.save(todo);
            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
