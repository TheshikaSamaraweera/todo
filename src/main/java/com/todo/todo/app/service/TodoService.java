package com.todo.todo.app.service;

import com.todo.todo.app.dto.TodoDto;
import com.todo.todo.app.entity.Todo;
import com.todo.todo.app.exception.ResourceNotFoundException;
import com.todo.todo.app.mapper.TodoMapper;
import com.todo.todo.app.reopsitory.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.todo.todo.app.mapper.TodoMapper.todoDtoToEntity;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;



    public TodoDto addTodo(TodoDto todoDto) {

        Todo todo = TodoMapper.todoDtoToEntity(todoDto);
        Todo savedTodo= todoRepository.save(todo);
        return TodoMapper.entityToTodoDto(savedTodo);

    }

    public List<TodoDto> getAllTodos(){
        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream().map(TodoMapper::entityToTodoDto)
                .collect(Collectors.toList());

    }


    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found" + id));
        return TodoMapper.entityToTodoDto(todo);
    }

    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found" + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.entityToTodoDto(updatedTodo);
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found" + id));
        todoRepository.delete(todo);
    }

    public TodoDto completeTodo(Long id){
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found" + id));
        todo.setCompleted(true);
        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.entityToTodoDto(updatedTodo);
    }

    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found" + id));
        todo.setCompleted(false);
        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.entityToTodoDto(updatedTodo);
    }
}
