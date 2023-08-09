package com.example.springboottodoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottodoapp.models.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}
