package com.todo.todo.app.controller;

import com.todo.todo.app.dto.TodoDto;
import com.todo.todo.app.service.TodoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todo")  // API endpoint for creating a new todo item
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
   @PostMapping
    public ResponseEntity<TodoDto> createTodo (@RequestBody TodoDto todoDto){
        TodoDto savedTodoDTO =todoService.addTodo(todoDto);
        return ResponseEntity.ok(savedTodoDTO);

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
       List<TodoDto> savedList= todoService.getAllTodos();
       return ResponseEntity.ok(savedList);

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id){
        TodoDto todoDto = todoService.getTodoById(id);
        return ResponseEntity.ok(todoDto);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDto todoDto){
        TodoDto updatedTodoDto = todoService.updateTodo(id, todoDto);
        return ResponseEntity.ok(updatedTodoDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Delete successfully" +id);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id ){
        TodoDto completedTodoDto = todoService.completeTodo(id);
        return ResponseEntity.ok(completedTodoDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER)")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id ){
        TodoDto inCompletedTodoDto = todoService.inCompleteTodo(id);
        return ResponseEntity.ok(inCompletedTodoDto);
    }



}
