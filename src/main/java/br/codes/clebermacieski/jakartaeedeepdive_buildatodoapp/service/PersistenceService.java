package br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.service;

import java.util.List;

import br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.Todo;
import br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.TodoUser;
import jakarta.annotation.sql.DataSourceDefinition;
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

		List list = queryService.countTodoUserByEmail(todoUser.getEmail());
		Long count = (Long) list.get(0);

		if (todoUser.getTodouser_id() == null && count == 0) {
			entityManager.persist(todoUser);
		}

		return todoUser;
	}

	public TodoUser updateTodoUser(TodoUser todoUser) {

		List list = queryService.countTodoUserByIdEmail(todoUser.getTodouser_id(), todoUser.getEmail());
		Integer count = (Integer) list.get(0);

		if (todoUser.getTodouser_id() != null && count == 1) {
			entityManager.merge(todoUser);
		}

		return todoUser;
	}
	
	public TodoUser updateTodoUserEmail(Long id, String email) {
		List list = queryService.countTodoUserByEmail(email);
		Integer count = (Integer) list.get(0);

		if(count != 0) {
			TodoUser todoUser = queryService.findTodoUser(id);
			if(todoUser != null) {
				todoUser.setEmail(email);
				entityManager.merge(todoUser);
				
				return todoUser;
			}
		}
		return null;
	}

	public Todo saveTodo(Todo todo) {

		String email = session.getEmail();
		TodoUser todoUser = queryService.findTodoUserByEmail(email);

		if (todo.getTodo_id() == null && queryService.findTodoUserByEmail(email) != null) {

			todo.setTodoOwner(todoUser);
			entityManager.persist(todo);
		} else {
			entityManager.merge(todo);
		}

		return todo;
	}
}
