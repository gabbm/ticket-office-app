package io.gabbm.ticket_office.reminder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gabbm.ticket_office.reminder.entity.ReminderTask;

public interface ReminderTaskRepository extends JpaRepository<ReminderTask, Long>{
	
	ReminderTask findById(long id);
	List<ReminderTask> findByType(String type);
}
