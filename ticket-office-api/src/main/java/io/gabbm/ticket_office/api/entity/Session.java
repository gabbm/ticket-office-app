package io.gabbm.ticket_office.api.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Session {

	/*
	 * Standard fields
	 */
	@Id
	public Integer id;
	
	// Date format: yyyy-MM-dd'T'HH:mm:ss.SSSZ, e.g. "2000-10-31T01:30:00.000-05:00"
	@DateTimeFormat(iso = ISO.DATE_TIME)
	public Date startDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	public Date endDate;
	public Double price;
	public Integer seating;
	public Integer freeSeating;
	// Session Id from Ticket Management Platform
	public String title;
	public String tmId;
	public String type;
	public String[] languages;
	
	/*
	 * Custom fields
	 */
	// Educational levels
	public Integer groupSeating;
	public Integer freeGroupSeating;
	public String activityId;
	// Activity Id from Ticket Management Platform
	public String activityTmId;
	// Museum
	public String centerId;
	public String comments;
	public String pricingType;
	public String spaceId;
	
	public Session() {}
	
	public Session(Integer id, Date startDate, Date endDate, Double price, Integer seating, Integer freeSeating,
			String title, String tmId, String type, String[] languages, Integer groupSeating, Integer freeGroupSeating,
			String activityId, String activityTmId, String centerId, String comments, String pricingType,
			String spaceId) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.seating = seating;
		this.freeSeating = freeSeating;
		this.title = title;
		this.tmId = tmId;
		this.type = type;
		this.languages = languages;
		this.groupSeating = groupSeating;
		this.freeGroupSeating = freeGroupSeating;
		this.activityId = activityId;
		this.activityTmId = activityTmId;
		this.centerId = centerId;
		this.comments = comments;
		this.pricingType = pricingType;
		this.spaceId = spaceId;
	}

	@Override
	public String toString(){
		return String.format(
				"Session[id=%s, tmId=%s, title='%s']", 
				id, tmId, title);
	}
}
