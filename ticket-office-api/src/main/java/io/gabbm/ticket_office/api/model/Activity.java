package io.gabbm.ticket_office.api.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.gabbm.ticket_office.util.Constants;

@Entity
public class Activity {
	
	/*
	 * Standard fields
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Long id;
		
	// Date format: yyyy-MM-dd'T'HH:mm:ss.SSSZ, e.g. "2000-10-31T01:30:00.000-05:00"
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "start_date", nullable = false)
	public Date startDate;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "end_date")
	public Date endDate;
	
	@Column(name = "price", nullable = true)
	public Double price = 0.0;
	
	@Column(name = "description", nullable = true)
	public String description;
	
	@Column(name = "image", nullable = true)
	public String image;

	@NotEmpty
	@Column(name = "title", nullable = false)
	public String title;
	
	// Activity Id from Ticket Management Platform
	@NotEmpty
	@Column(name="tm_id", nullable = false)
	public String tmId;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	public Constants.ActivityType type;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "languages", joinColumns = @JoinColumn(name = "activity_id", nullable = true))
	@Column(name = "language")
	@Enumerated(EnumType.STRING)
	private Set<Constants.Language> languages = new HashSet<Constants.Language>();
	
	@OneToMany(fetch = FetchType.LAZY, 
			mappedBy = "activity",
			orphanRemoval = true)
	@Cascade({CascadeType.DELETE})
    private Set<Session> sessions = new HashSet<Session>();
	
	/*
	 * Custom fields
	 */
	// Educational levels
	@Column(name = "earlyChildhoodEducation", nullable = true)
	public boolean earlyChildhoodEducation;
	
	@Column(name = "lowerPrimaryEducation", nullable = true)
	public boolean lowerPrimaryEducation;
	
	@Column(name = "middlePrimaryEducation", nullable = true)
	public boolean middlePrimaryEducation;
	
	@Column(name = "upperPrimaryEducation", nullable = true)
	public boolean upperPrimaryEducation;
	
	@Column(name = "lowerSecondaryEducation", nullable = true)
	public boolean lowerSecondaryEducation;
	
	@Column(name = "upperSecondaryEducation", nullable = true)
	public boolean upperSecondaryEducation;
	
	@Column(name = "postSecondaryEducation", nullable = true)
	public boolean postSecondaryEducation;
	
	@Column(name = "specialEducation", nullable = true)
	public boolean specialEducation;
	
	@Column(name = "summerCamp", nullable = true)
	public boolean summerCamp;
	
	// Museum or space Id
	@NotEmpty
	@Column(name = "centerId", nullable = false)
	public String centerId;
	
	@Column(name = "comments", nullable = true)
	public String comments;
	
	@Column(name = "pricingType", nullable = true)
	@Enumerated(EnumType.STRING)
	public Constants.PricingType pricingType;

	public Activity(){}
	
	public Activity(Long id, Date startDate, Date endDate, Double price, String description, String image,
			String title, String tmId, Constants.ActivityType type, Set<Constants.Language> languages, boolean earlyChildhoodEducation,
			boolean lowerPrimaryEducation, boolean middlePrimaryEducation, boolean upperPrimaryEducation,
			boolean lowerSecondaryEducation, boolean upperSecondaryEducation, boolean postSecondaryEducation,
			boolean specialEducation, boolean summerCamp, String centerId, String comments, Constants.PricingType pricingType, Set<Session> sessions) {
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
		this.sessions = sessions;
	}
	
	@Override
	public String toString(){
		return String.format(
				"Activity[id=%s, tmId=%s, title='%s']", 
				id, tmId, title);
	}
}
