package codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity;

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
@NamedQuery(name = Todo.FIND_ALL_TODOS_BY_ONWER_EMAIL, query = "select todo from Todo  todo where todo.todoOwner.email = :email")
public class Todo extends AbstractEntity {

	public static final String FIND_ALL_TODOS_BY_ONWER_EMAIL = "Todo.findAllByEmail";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long todo_id;

	@NotEmpty(message = "Não pode ser vazio.")
	@Size(min = 3, max = 140, message = "Minmo 3, máximo 140.")
	private String task;
	
	private LocalDate dateCreated;
	
	@NotNull(message = "Deve ser no presente ou futuro.")
	@FutureOrPresent(message = "Tem que ser no presente ou futuro.")
	@JsonbDateFormat(value = "DD-MM-YYY")
	private LocalDate dueDate;
	
	private int completed;
	private int archived;
	private int remind;

	@ManyToOne
	@JoinColumn(name = "todouser_id")
	private TodoUser todoOwner;

	public Long getTodo_id() {
		return todo_id;
	}

	public void setTodo_id(Long todo_id) {
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

	public int isCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int isArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public int isRemind() {
		return remind;
	}

	public void setRemind(int remind) {
		this.remind = remind;
	}

	public TodoUser getTodoOwner() {
		return todoOwner;
	}

	public void setTodoOwner(TodoUser todoOwner) {
		this.todoOwner = todoOwner;
	}

}
