package com.example.helidonTodoAPP.controller;


import com.example.helidonTodoAPP.model.Todo;
import com.example.helidonTodoAPP.repo.TodoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    private TodoRepository repository;

    @GET
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @GET
    @Path("/{id}")
    public Todo getTodoById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @POST
    public Todo createTodo(Todo todo) {
        return repository.create(todo);
    }

    @PUT
    @Path("/{id}")
    public Todo updateTodo(@PathParam("id") Long id, Todo todo) {
        return repository.update(id, todo);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTodo(@PathParam("id") Long id) {
        repository.delete(id);
    }
}
