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
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.gabbm.ticket_office.util.Constants;

@Entity
public class Session {

	/*
	 * Standard fields
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer id;
	
	// Date format: yyyy-MM-dd'T'HH:mm:ss.SSSZ, e.g. "2000-10-31T01:30:00.000-05:00"
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "start_date", nullable = false)
	public Date startDate;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "end_date")
	public Date endDate;
	
	@NotEmpty
	@Column(name = "price", nullable = false)
	public Double price = 0.0;
	
	@NotEmpty
	@Column(name = "seating", nullable = true)
	public Integer seating = 0;
	
	@NotEmpty
	@Column(name = "freeSeating", nullable = true)
	public Integer freeSeating = 0;
	
	@NotEmpty
	@Column(name = "title", nullable = false)
	public String title;
	
	// Session Id from Ticket Management Platform
	@NotEmpty
	@Column(name="tm_id", nullable = false)
	public String tmId;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	public Constants.SessionType type;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "languages", joinColumns = @JoinColumn(name = "session_id", nullable = true))
	@Column(name = "language")
	@Enumerated(EnumType.STRING)
	private Set<Constants.Language> languages = new HashSet<Constants.Language>();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    public Activity activity;
	
	/*
	 * Custom fields
	 */
	// Educational levels
	@Column(name = "groupSeating", nullable = true)
	public Integer groupSeating = 0;
	
	@Column(name = "freeGroupSeating", nullable = true)
	public Integer freeGroupSeating = 0;
	
	// Museum
	@Column(name = "comments", nullable = true)
	public String comments;
	
	@Column(name = "pricingType", nullable = true)
	@Enumerated(EnumType.STRING)
	public Constants.PricingType pricingType;
	
	@Column(name = "spaceId", nullable = true)
	public String spaceId;
	
	public Session() {}
	
	public Session(Integer id, Date startDate, Date endDate, Double price, Integer seating, Integer freeSeating,
			String title, String tmId, Constants.SessionType type, Set<Constants.Language> languages, Integer groupSeating, Integer freeGroupSeating,
			Activity activity, String comments, Constants.PricingType pricingType, String spaceId) {
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
		this.activity = activity;
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
