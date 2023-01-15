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
@NamedQuery(name =TodoUser.FIND_TODO_USER_BY_EMAIL, query = "select tU from TodoUser  tU where tU.email = :email")

@NamedQuery(name = TodoUser.FIND_ALL_TODO_USERS, query = "select todoUser from TodoUser  todoUser order by todoUser.fullName") //select * from TodoUserTable...
@NamedQuery(name = TodoUser.FIND_TODO_USER_BY_ID, query = "select t from TodoUser t where t.todouser_id = :todouser_id and t.email = :email")
@NamedQuery(name = TodoUser.FIND_TODO_BY_NAME, query = "select t from TodoUser t where t.fullName like :name ")public class TodoUser extends AbstractEntity {

    public static final String FIND_TODO_USER_BY_EMAIL = "TodoUser.findByEmail";
    public static final String FIND_ALL_TODO_USERS = "TodoUser.findAll";
    public static final String FIND_TODO_USER_BY_ID = "TodoUser.findByIdAndEmail";
    public static final String FIND_TODO_BY_NAME = "TodoUser.findByName";
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