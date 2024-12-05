package com.todo.todo.app.service;

import com.todo.todo.app.dto.TodoDto;
import com.todo.todo.app.entity.Todo;
import com.todo.todo.app.mapper.TodoMapper;
import com.todo.todo.app.reopsitory.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




}
