package io.gabbm.ticket_office.reminder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gabbm.ticket_office.reminder.entity.Template;

public interface TemplateRepository extends JpaRepository<Template, Long>{
	
	Template findById(long id);
	List<Template> findByLanguage(String language);
}