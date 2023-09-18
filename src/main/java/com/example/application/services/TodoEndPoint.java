package com.example.application.services;

import com.example.application.Todo;
import com.example.application.TodoRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Endpoint
@AnonymousAllowed
@Service
public class TodoEndPoint {
    private final TodoRepository todoRepository;

    public TodoEndPoint(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    public List<Todo> findAll(){
        return todoRepository.findAll();
    }
    public Todo add(String task){
        return todoRepository.save(new Todo(task));
    }
    public Todo update(Todo todo){
        return todoRepository.save(todo);
    }
}
