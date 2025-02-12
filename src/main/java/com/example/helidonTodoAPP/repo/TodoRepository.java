package com.example.helidonTodoAPP.repo;


import com.example.helidonTodoAPP.model.Todo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TodoRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<Todo> findAll() {
        return em.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
    }

    @Transactional
    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    @Transactional
    public Todo create(Todo todo) {
        em.persist(todo);
        return todo;
    }

    @Transactional
    public Todo update(Long id, Todo updated) {
        Todo existing = em.find(Todo.class, id);
        if (existing != null) {
            existing.setTitle(updated.getTitle());
            existing.setCompleted(updated.isCompleted());
            return em.merge(existing);
        }
        return null;
    }

    @Transactional
    public void delete(Long id) {
        Todo existing = em.find(Todo.class, id);
        if (existing != null) {
            em.remove(existing);
        }
    }
}