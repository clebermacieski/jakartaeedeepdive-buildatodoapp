package br.codes.clebermacieski.jakartaeedeepdive_buildatodoapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TodoUser extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long todouser_id;
	
	@Column(length= 100)
	private String email;
	private String password;
	private String fullName;
	
//	@OneToMany
//	private final Collection<Todo> todos = new ArrayList<>();
	
	public long getId() {
		return todouser_id;
	}
	public void setId(long id) {
		this.todouser_id = id;
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