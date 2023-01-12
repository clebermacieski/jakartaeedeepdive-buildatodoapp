package br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Todo extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long todo_id;
	
	private String task;
	private LocalDate dateCreated;
	private LocalDate dueDate;
	private boolean completed;
	private boolean archived;
	private boolean remind;
	
	@ManyToOne
	@JoinColumn(name = "todouser_id")
	private TodoUser todoOwner;

	public long getTodo_id() {
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
