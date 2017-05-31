package io.gabbm.ticket_office.reminder.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import io.gabbm.ticket_office.reminder.util.Constants;

@Entity
@Table(name="Users", indexes = {
		@Index(columnList = "email", unique = true)
})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/*
	 * Should be unique
	 */
	@Column(nullable = false, length = Constants.EMAIL_MAX)
	private String email;
	
	@Column(nullable = false, length = Constants.NAME_MAX)
	private String name;
	
	/*
	 * No length because it will be encrypted
	 */
	@Column(nullable = false, length = Constants.PASSWORD_MAX)
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Constants.Role> roles = new HashSet<Constants.Role>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Constants.Role> getRoles() {
		return roles;
	}
}
