package com.marujaangela.webtech_project;

public class Todo {

    private String taskDescription;
    private boolean completed;

    // Konstruktor mit Parametern
    public Todo(String taskDescription, boolean completed) {
        this.taskDescription = taskDescription;
        this.completed = completed;
    }

    // Getter und Setter

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