package com.marujaangela.webtech_project;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {
    @GetMapping(path = "/todo")
    public ResponseEntity<Todo[]> getTodo() {
        final Todo todo1 = new Todo("Statistik HA", false);
    }
    final Todo todo2 = new Todo("Mathe HA", true);
}
