package com.example.springboottodoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboottodoapp.models.TodoItem;
import com.example.springboottodoapp.services.TodoItemService;

@Controller
public class HomeController {

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemService.getAll());
        return modelAndView;
    }

    @GetMapping("/toggle-completion/{id}")
    public String toggleCompletion(@PathVariable("id") Long id) {
        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id:" + id + " not found"));

        todoItem.setCompleted(!todoItem.isCompleted());
        todoItemService.save(todoItem);

        return "redirect:/"; // Redirect back to the home page
    }
}
