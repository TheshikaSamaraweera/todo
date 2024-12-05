package com.todo.todo.app.mapper;

import com.todo.todo.app.dto.TodoDto;
import com.todo.todo.app.entity.Todo;

public class TodoMapper {


    public static TodoDto entityToTodoDto(Todo todo){
        TodoDto todoDto = new TodoDto();
        todoDto.setId(todo.getId());
        todoDto.setTitle(todo.getTitle());
        todoDto.setDescription(todo.getDescription());
        todoDto.setCompleted(todo.isCompleted());
        return todoDto;
    }


    public static Todo todoDtoToEntity(TodoDto todoDto){
        Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        return todo;
    }
}
