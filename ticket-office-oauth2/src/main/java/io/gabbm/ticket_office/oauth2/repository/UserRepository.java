package io.gabbm.ticket_office.oauth2.repository;

import org.springframework.data.repository.CrudRepository;

import io.gabbm.ticket_office.oauth2.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByLogin(String login);
}
