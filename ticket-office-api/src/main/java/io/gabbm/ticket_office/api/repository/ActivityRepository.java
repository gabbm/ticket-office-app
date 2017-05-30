package io.gabbm.ticket_office.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.gabbm.ticket_office.api.entity.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String>{

	public Activity findById(String id);
	public Activity findByTmId(String tmId);
	public List<Activity> findByType(String type);
	public List<Activity> findByCenterId(String centerId);
	public List<Activity> findByStartDateBetween(Date from, Date to);
	public List<Activity> findByStartDateBetweenAndCenterId(Date from, Date to, String centerId);
}
