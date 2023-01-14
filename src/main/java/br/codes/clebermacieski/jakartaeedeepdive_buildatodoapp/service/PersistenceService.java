package br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.service;

import br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.Todo;
import br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.TodoUser;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PersistenceService {

	@Inject
	private Session session;
	
	@Inject
	private QueryService queryService;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public TodoUser saveTodoUser(TodoUser todoUser) {
		if(todoUser.getTodouser_id() == null) {
			entityManager.persist(todoUser);			
		}
		else {
			entityManager.merge(todoUser);
		}
		
		return todoUser;
	}

	public Todo saveTodo(Todo todo) {
						
		todo.setTodoOwner(queryService.findTodoUserByEmail(session.getEmail()));
		
		if(todo.getTodo_id() == null) {
			entityManager.persist(todo);			
		}
		else {
			entityManager.merge(todo);
		}
		
		return todo;
	}
}
