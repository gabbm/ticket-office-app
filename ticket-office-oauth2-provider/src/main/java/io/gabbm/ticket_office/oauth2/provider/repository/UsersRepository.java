package io.gabbm.ticket_office.oauth2.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gabbm.ticket_office.oauth2.provider.entity.Users;

/**
 * JPA Repository for Users queries
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByLogin(String login);
}