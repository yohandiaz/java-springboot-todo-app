package com.example.springboottodoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboottodoapp.models.TodoItem;
import com.example.springboottodoapp.services.TodoItemService;

import jakarta.validation.Valid;

@Controller
public class TodoFormController {

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem) {
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
        // Create a new TodoItem and set its properties
        TodoItem item = new TodoItem();
        item.setDescription(todoItem.getDescription());
        item.setCompleted(todoItem.isCompleted());

        // Save the new TodoItem using the service
        todoItemService.save(todoItem);
        
        // Redirect back to the home page
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model){
        // Retrieve the TodoItem by id and delete it using the service
        TodoItem todoItem = todoItemService
                    .getById(id)
                    .orElseThrow(() -> new IllegalArgumentException("TodoItem id:" + id + " not found"));
        
        todoItemService.delete(todoItem);
        
        // Redirect back to the home page
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        // Retrieve the TodoItem by id and pass it to the edit form
        TodoItem todoItem = todoItemService
                    .getById(id)
                    .orElseThrow(() -> new IllegalArgumentException("TodoItem id:" + id + " not found"));

        model.addAttribute("todo", todoItem);
        
        // Redirect to the edit form page
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItemString(@PathVariable("id") Long id, @Valid TodoItem todoItem, BindingResult result, Model model){
        // Retrieve the TodoItem by id, update its properties, and save using the service
        TodoItem item = todoItemService
                    .getById(id)
                    .orElseThrow(() -> new IllegalArgumentException("TodoItem id:" + id + " not found"));

        item.setCompleted(todoItem.isCompleted());
        item.setDescription(todoItem.getDescription());
        
        todoItemService.save(item);

        // Redirect back to the home page
        return "redirect:/";
    }
}
