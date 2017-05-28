package io.gabbm.ticket_office.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.gabbm.ticket_office.api.entity.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String>{

	public Activity findByTmId(String tmId);
	public Activity findByType(String type);
}
