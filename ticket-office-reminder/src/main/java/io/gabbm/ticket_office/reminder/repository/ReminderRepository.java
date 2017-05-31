package io.gabbm.ticket_office.reminder.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gabbm.ticket_office.reminder.entity.Reminder;
import io.gabbm.ticket_office.reminder.entity.ReminderTask;

public interface ReminderRepository extends JpaRepository<ReminderTask, Long>{
	
	Reminder findById(long id);
	List<Reminder> findByLanguage(String language);
	List<Reminder> findByOrder(String order);
	List<Reminder> findByReminderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	List<Reminder> findByToEmail(String email);
	List<Reminder> findByType(String type);
}
