package io.gabbm.ticket_office.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.gabbm.ticket_office.api.repository.Users;
import io.gabbm.ticket_office.oauth2.repository.UserRepository;

@RestController
public class UserController {

	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping("/users")
	public Iterable<Users> getUsers() {
		return userRepository.findAll();
	}

}
