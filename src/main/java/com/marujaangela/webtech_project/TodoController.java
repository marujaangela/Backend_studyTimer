package com.marujaangela.webtech_project;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {
    @GetMapping(path = "/todos")
    public ResponseEntity<Todo[]> getTodos() {
        final Todo todo1 = new Todo("Statistik HA", false);
        final Todo todo2 = new Todo("Mathe HA", true);

        Todo[] todos = {todo1, todo2};
        return ResponseEntity.ok(todos);
    }
}