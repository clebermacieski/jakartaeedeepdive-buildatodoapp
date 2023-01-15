package br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.service;

import java.util.Collection;
import java.util.List;

import br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.Todo;
import br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.TodoUser;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class QueryService {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    private Session mySession;

    public TodoUser findTodoUserByEmail(String email) {

        return entityManager.createNamedQuery(TodoUser.FIND_TODO_USER_BY_EMAIL, TodoUser.class)
                .setParameter("email", email).getSingleResult();


    }

    public List<TodoUser> findAllTodoUsers() {

        return entityManager.createNamedQuery(TodoUser.FIND_ALL_TODO_USERS, TodoUser.class).getResultList();
    }


    public TodoUser findTodoUserById(Long id) {

        return entityManager.createNamedQuery(TodoUser.FIND_TODO_USER_BY_ID, TodoUser.class)
                .setParameter("id", id).setParameter("email", mySession.getEmail()).getSingleResult();
    }

    public Collection<TodoUser> findTodoUsersByName(String name) {

        return entityManager.createNamedQuery(TodoUser.FIND_TODO_BY_NAME, TodoUser.class)
                .setParameter("name", "%" + name + "%").getResultList(); //jo
    }


    public Collection<Todo> findAllTodos(String email) {
        return entityManager.createNamedQuery(Todo.FIND_ALL_TODOS_BY_ONWER_EMAIL, Todo.class)
                .setParameter("email", email).getResultList();
    }

}
