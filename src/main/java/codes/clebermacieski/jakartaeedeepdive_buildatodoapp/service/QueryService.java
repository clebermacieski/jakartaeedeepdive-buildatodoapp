package codes.clebermacieski.jakartaeedeepdive_buildatodoapp.service;

import java.util.Collection;
import java.util.List;

import codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.Todo;
import codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity.TodoUser;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class QueryService {

	@PersistenceContext
	EntityManager entityManager;

	@Inject
	private Session mySession;

	public TodoUser findTodoUserByEmail(String email) {

		try {
			return entityManager.createNamedQuery(TodoUser.FIND_TODO_USER_BY_EMAIL, TodoUser.class)
					.setParameter("email", email).getSingleResult();
		} catch (NonUniqueResultException | NoResultException e) {
			return null;
		}
	}

	public List<TodoUser> findAllTodoUsers() {

		return entityManager.createNamedQuery(TodoUser.FIND_ALL_TODO_USERS, TodoUser.class).getResultList();
	}

	public TodoUser findTodoUserById(Long id) {

		try {
			return entityManager.createNamedQuery(TodoUser.FIND_TODO_USER_BY_ID, TodoUser.class).setParameter("id", id)
					.setParameter("email", mySession.getEmail()).getSingleResult();
		} catch (NonUniqueResultException | NoResultException e) {
			return null;
		}
	}

	public Collection<TodoUser> findTodoUsersByName(String name) {

		return entityManager.createNamedQuery(TodoUser.FIND_TODO_BY_NAME, TodoUser.class)
				.setParameter("name", "%" + name + "%").getResultList();
	}

	public Collection<Todo> findAllTodos(String email) {
		return entityManager.createNamedQuery(Todo.FIND_ALL_TODOS_BY_ONWER_EMAIL, Todo.class)
				.setParameter("email", email).getResultList();
	}

	public List countTodoUserByEmail(String email) {
		List resultList = entityManager.createNativeQuery(
				"select count(todouser_id) from TodoUser where  exists (select todouser_id from TodoUser where email = ?)")
				.setParameter(1, email).getResultList();

		return resultList;
	}

	public List countTodoUserByIdEmail(Long id, String email) {
		return entityManager.createNativeQuery(
				"select count(todouser_id) from TodoUser where  exists (select todouser_id from TodoUser where todouser_id = ?1 and email = ?2)")
				.setParameter(1, id).setParameter(2, email).getResultList();
	}
	
	public TodoUser findTodoUser(Long todouser_id) {
		return entityManager.find(TodoUser.class, todouser_id);
	}

}
