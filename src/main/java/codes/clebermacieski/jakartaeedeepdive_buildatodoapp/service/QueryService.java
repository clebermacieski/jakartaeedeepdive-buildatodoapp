package codes.clebermacieski.jakartaeedeepdive_buildatodoapp.service;

import java.time.LocalDate;
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
import jakarta.validation.constraints.NotNull;

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

	public Collection<Todo> findAllTodos() {
		return entityManager.createNamedQuery(Todo.FIND_ALL_TODOS_BY_ONWER_EMAIL, Todo.class)
				.setParameter("email", mySession.getEmail()).getResultList();
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

	public Todo findTodoById(Long todo_id) {
		List<Todo> resultList = entityManager
				.createQuery("SELECT t FROM Todo t WHERE t.todo_id = :todo_id AND t.todoOwner.email = :email",
						Todo.class)
				.setParameter("todo_id", todo_id).setParameter("email", mySession.getEmail()).getResultList();

		var ret = resultList.isEmpty() ? resultList.get(0) : null;

		return ret;
	}

	public void markTodoAsCompleted(Long todo_id) {
		// Update directly the entity
//		entityManager.createQuery("UPDATE Todo t set t.completed = true").executeUpdate();
		Todo todoById = findTodoById(todo_id);

		if (todoById != null) {
			todoById.setCompleted(1);
			entityManager.merge(todoById);
		}
	}

	public List<Todo> getAllCompletedTodos() {
		return getAllUncompletedgetTodoByState(1);
	}

	public List<Todo> getAllUncompletedTodos() {
		return getAllUncompletedgetTodoByState(0);
	}

	private List<Todo> getAllUncompletedgetTodoByState(int state) {
		return entityManager
				.createQuery("SELECT t FROM Todo t WHERE t.todoOwner.email = :email AND t.completed = :state",
						Todo.class)
				.setParameter("email", mySession.getEmail()).setParameter("state", state).getResultList();
	}

	public List<Todo> getTodosByDueDate(LocalDate dueDate) {
		return entityManager
				.createQuery("SELECT t FROM Todo t WHERE t.todoOwner.email = :email AND t.dueDate = :dueDate",
						Todo.class)
				.setParameter("email", mySession.getEmail()).setParameter("dueDate", dueDate).getResultList();
	}

	public void markTodoAsArchived(@NotNull Long todo_id) {
		Todo todoById = findTodoById(todo_id);

		if (todoById != null) {
			todoById.setArchived(1);
			entityManager.merge(todoById);
		}
	}

}
