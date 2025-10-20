package com.example.todoapp.controller;

import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*") // Allows frontend to access this API
public class TodoController {

    @Autowired
    private TodoService todoService;
     @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Welcome to TodoApp API! Use /api/todos to access todos.");
    }


    // GET all todos - http://localhost:8080/api/todos
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    // GET todo by ID - http://localhost:8080/api/todos/1
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new todo - http://localhost:8080/api/todos
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    // PUT update todo - http://localhost:8080/api/todos/1
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        try {
            Todo updatedTodo = todoService.updateTodo(id, todoDetails);
            return ResponseEntity.ok(updatedTodo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE todo - http://localhost:8080/api/todos/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET completed todos - http://localhost:8080/api/todos/completed
    @GetMapping("/completed")
    public ResponseEntity<List<Todo>> getCompletedTodos() {
        return ResponseEntity.ok(todoService.getCompletedTodos());
    }

    // GET incomplete todos - http://localhost:8080/api/todos/incomplete
    @GetMapping("/incomplete")
    public ResponseEntity<List<Todo>> getIncompleteTodos() {
        return ResponseEntity.ok(todoService.getIncompleteTodos());
    }

    // GET search todos - http://localhost:8080/api/todos/search?keyword=test
    @GetMapping("/search")
    public ResponseEntity<List<Todo>> searchTodos(@RequestParam String keyword) {
        return ResponseEntity.ok(todoService.searchTodos(keyword));
    }

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Todo API is running!");
    }
}
