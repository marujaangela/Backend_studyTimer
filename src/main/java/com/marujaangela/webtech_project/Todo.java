package com.marujaangela.webtech_project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primärschlüssel hinzugefügt

    private String taskDescription;
    private boolean completed;

    // No-arg Konstruktor (erforderlich für JPA)
    public Todo() {
    }

    // Konstruktor mit Parametern
    public Todo(String taskDescription, boolean completed) {
        this.taskDescription = taskDescription;
        this.completed = completed;
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}