package io.gabbm.ticket_office.api.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Activity {
	
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
	public String description;
	public String image;
	// Activity Id from Ticket Management Platform
	public String title;
	public String tmId;
	public String type;
	public String[] languages;	
	
	/*
	 * Custom fields
	 */
	// Educational levels
	public boolean earlyChildhoodEducation;
	public boolean lowerPrimaryEducation;
	public boolean middlePrimaryEducation;
	public boolean upperPrimaryEducation;
	public boolean lowerSecondaryEducation;
	public boolean upperSecondaryEducation;
	public boolean postSecondaryEducation;
	public boolean specialEducation;
	public boolean summerCamp;
	// Museum or space Id
	public String centerId;
	public String comments;
	public String pricingType;

	public Activity(){}
	
	public Activity(Integer id, Date startDate, Date endDate, Double price, String description, String image,
			String title, String tmId, String type, String[] languages, boolean earlyChildhoodEducation,
			boolean lowerPrimaryEducation, boolean middlePrimaryEducation, boolean upperPrimaryEducation,
			boolean lowerSecondaryEducation, boolean upperSecondaryEducation, boolean postSecondaryEducation,
			boolean specialEducation, boolean summerCamp, String centerId, String comments, String pricingType) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.description = description;
		this.image = image;
		this.title = title;
		this.tmId = tmId;
		this.type = type;
		this.languages = languages;
		this.earlyChildhoodEducation = earlyChildhoodEducation;
		this.lowerPrimaryEducation = lowerPrimaryEducation;
		this.middlePrimaryEducation = middlePrimaryEducation;
		this.upperPrimaryEducation = upperPrimaryEducation;
		this.lowerSecondaryEducation = lowerSecondaryEducation;
		this.upperSecondaryEducation = upperSecondaryEducation;
		this.postSecondaryEducation = postSecondaryEducation;
		this.specialEducation = specialEducation;
		this.summerCamp = summerCamp;
		this.centerId = centerId;
		this.comments = comments;
		this.pricingType = pricingType;
	}
	
	@Override
	public String toString(){
		return String.format(
				"Activity[id=%s, tmId=%s, title='%s']", 
				id, tmId, title);
	}
}
