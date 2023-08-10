package com.example.springboottodoapp.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboottodoapp.models.TodoItem;
import com.example.springboottodoapp.repositories.TodoItemRepository;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    // Method to get all todo items from the repository
    public Iterable<TodoItem> getAll() {
        return todoItemRepository.findAll();
    }

    // Method to get a todo item by its unique id
    public Optional<TodoItem> getById(Long id) {
        return todoItemRepository.findById(id);
    }

    // Method to save a todo item to the repository
    public TodoItem save(TodoItem todoItem) {
        // If the todo item doesn't have an ID, set its creation time
        if (todoItem.getId() == null) {
            todoItem.setCreatedAt(LocalDateTime.now());
        }

        // Set the update time to the current time
        todoItem.setUpdatedAt(LocalDateTime.now());

        // Save the todo item in the repository and return the saved instance
        return todoItemRepository.save(todoItem);
    }

    // Method to delete a todo item from the repository
    public void delete(TodoItem todoItem) {
        todoItemRepository.delete(todoItem);
    }
}
