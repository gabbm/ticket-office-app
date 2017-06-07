package io.gabbm.ticket_office.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gabbm.ticket_office.api.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long>{

	public Activity findById(Long id);
	public Activity findByTmId(String tmId);
	public List<Activity> findByType(String type);
	public List<Activity> findByCenterId(String centerId);
	public List<Activity> findByStartDateBetween(Date from, Date to);
	public List<Activity> findByStartDateBetweenAndCenterId(Date from, Date to, String centerId);
}
