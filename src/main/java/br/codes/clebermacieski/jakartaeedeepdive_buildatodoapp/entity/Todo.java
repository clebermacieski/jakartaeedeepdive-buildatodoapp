package br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity;

import java.time.LocalDate;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@NamedQuery(name = Todo.FIND_ALL_TODOS_BY_EMAIL, query = "SELECT Todo FROM Todo WHERE Todo.todoOwner.email = :email")
public class Todo extends AbstractEntity {

	public static final String FIND_ALL_TODOS_BY_EMAIL = "FIND_ALL_TODOS";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long todo_id;

	@NotEmpty(message = "Não pode ser vazio.")
	@Size(min = 3, max = 140, message = "Minmo 3, máximo 140.")
	private String task;
	
	private LocalDate dateCreated;
	
	@NotNull(message = "Deve ser no presente ou futuro.")
	@FutureOrPresent(message = "Tem que ser no presente ou futuro.")
	@JsonbDateFormat(value = "DD-MM-YYY")
	private LocalDate dueDate;
	
	private boolean completed;
	private boolean archived;
	private boolean remind;

	@ManyToOne
	@JoinColumn(name = "todouser_id")
	private TodoUser todoOwner;

	public Long getTodo_id() {
		return todo_id;
	}

	public void setTodo_id(long todo_id) {
		this.todo_id = todo_id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public boolean isRemind() {
		return remind;
	}

	public void setRemind(boolean remind) {
		this.remind = remind;
	}

	public TodoUser getTodoOwner() {
		return todoOwner;
	}

	public void setTodoOwner(TodoUser todoOwner) {
		this.todoOwner = todoOwner;
	}

}
