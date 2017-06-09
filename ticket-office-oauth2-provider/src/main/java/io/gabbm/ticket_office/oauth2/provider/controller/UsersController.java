package io.gabbm.ticket_office.oauth2.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.gabbm.ticket_office.oauth2.provider.entity.Users;
import io.gabbm.ticket_office.oauth2.provider.repository.UsersRepository;

@RestController
@RequestMapping("/api/admin")
public class UsersController {

	private final UsersRepository usersRepository;

	@Autowired
	public UsersController(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@RequestMapping("/users")
	public Iterable<Users> getUsers() {
		return usersRepository.findAll();
	}

}
