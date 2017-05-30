package io.gabbm.ticket_office.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.gabbm.ticket_office.api.entity.Activity;
import io.gabbm.ticket_office.api.entity.Session;

public interface SessionRepository extends MongoRepository<Activity, String>{

	public Session findById(String id);
	public Session findByTmId(String tmId);
	public List<Session> findByType(String type);
	public List<Session> findByActivityId(String activityId);
	public List<Session> findByActivityTmId(String activityTmId);
	public List<Session> findByCenterId(String centerId);
	public List<Session> findByStartDateBetween(Date from, Date to);
	public List<Session> findByStartDateBetweenAndActivityId(Date from, Date to, String activityId);
	public List<Session> findByStartDateBetweenAndActivityTmId(Date from, Date to, String activityTmId);
	public List<Session> findByStartDateBetweenAndCenterId(Date from, Date to, String centerId);
}
