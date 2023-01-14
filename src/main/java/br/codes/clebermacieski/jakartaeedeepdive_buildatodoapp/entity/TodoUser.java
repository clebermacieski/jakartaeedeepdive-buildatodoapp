package br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@NamedQuery(name = TodoUser.FIND_TODO_USER_BY_EMAIL, query = "SELECT todoUser FROM TodoUser WHERE email = :email")
@NamedQuery(name = TodoUser.FIND_ALL_TODO_USERS, query = "SELECT todoUser FROM TodoUser ORDER BY fullName")
@NamedQuery(name = TodoUser.FIND_TODO_USER_BY_ID, query = "SELECT todoUser FROM TodoUser WHERE id = :id")
@NamedQuery(name = TodoUser.FIND_TODO_USER_BY_NAME, query = "SELECT todoUser FROM TodoUser WHERE fullName LIKE :name")
public class TodoUser extends AbstractEntity {

	public static final String FIND_TODO_USER_BY_EMAIL = "TODO_USER_BY_EMAIL";
	public static final String FIND_ALL_TODO_USERS = "FIND_ALL_TODO_USERS";
	public static final String FIND_TODO_USER_BY_ID = "FIND_TODO_USER_BY_ID";
	public static final String FIND_TODO_USER_BY_NAME = "FIND_TODO_USER_BY_NAME";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long todouser_id;

	@Column(length = 100)
	@NotEmpty(message = "Não pode ser vazio.")
	@Email(message = "algo@algo.")
	private String email;

	@NotNull(message = "Não pode ser vazio.")
	@Size(min = 8, max = 100)
	/*
	 * @Pattern(regexp = "", message =
	 * "A senha deve ter pelo menos uma letra maiuscula" +
	 * ", uma minuscula, um dígito e um carácter $%&#!.")
	 */
	private String password;

	@NotEmpty(message = "Deve ser informado.")
	@Size(min = 2, max = 150, message = "Mínimo 2, máximo 150.")
	private String fullName;

//	@OneToMany
//	private final Collection<Todo> todos = new ArrayList<>();

	public Long getTodouser_id() {
		return todouser_id;
	}

	public void setTodouser_id(long todouser_id) {
		this.todouser_id = todouser_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}