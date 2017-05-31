package io.gabbm.ticket_office.reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.gabbm.ticket_office.reminder.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	/*
	 * Spring Data creates it automatically parsing the name
	 * We can use the @Query annotation to write the JPA query
	 * We can also use the @Query annotation to write SQL queries enabling nativeQuery=true
	 * We also can use named parameters instead ?x, example --> :email
	 */
	//User findByEmail(String email);
	@Query("select u from Users u where u.email = ?1")
	User findByEmail(String email);
}