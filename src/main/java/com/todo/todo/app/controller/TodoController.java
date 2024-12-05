package com.todo.todo.app.controller;

import com.todo.todo.app.dto.TodoDto;
import com.todo.todo.app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")  // API endpoint for creating a new todo item
public class TodoController {

    @Autowired
    private TodoService todoService;

   @PostMapping
    public ResponseEntity<TodoDto> createTodo (@RequestBody TodoDto todoDto){
        TodoDto savedTodoDTO =todoService.addTodo(todoDto);
        return ResponseEntity.ok(savedTodoDTO);

    }


}
