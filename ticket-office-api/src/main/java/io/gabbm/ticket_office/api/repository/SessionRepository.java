package io.gabbm.ticket_office.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gabbm.ticket_office.api.model.Activity;
import io.gabbm.ticket_office.api.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{

	public Session findById(Long id);
	public Session findByTmId(String tmId);
	public List<Session> findByType(String type);
	public List<Session> findByActivity(Activity activity);
	public List<Session> findByStartDateBetween(Date from, Date to);
	public List<Session> findByStartDateBetweenAndActivity(Date from, Date to, Activity activity);
	public List<Session> findByStartDateBetweenAndActivityTmId(Date from, Date to, String activityTmId);
}
