package io.gabbm.ticket_office.reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gabbm.ticket_office.reminder.entity.File;

public interface FileRepository extends JpaRepository<File, Long>{
	
	File findById(long id);
}