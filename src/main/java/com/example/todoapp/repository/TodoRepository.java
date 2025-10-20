package com.example.todoapp.repository;

import com.example.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Spring Data JPA automatically implements these methods

    // Find all completed or incomplete todos
    List<Todo> findByCompleted(Boolean completed);

    // Find todos by title containing a keyword (case-insensitive)
    List<Todo> findByTitleContainingIgnoreCase(String keyword);
}
