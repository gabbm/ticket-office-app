package io.gabbm.ticket_office.oauth2.repository;

import org.springframework.data.repository.CrudRepository;

import io.gabbm.ticket_office.api.repository.Users;

public interface UserRepository extends CrudRepository<Users, Long> {

	Users findByUsername(String username);
}
