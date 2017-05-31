package io.gabbm.ticket_office.reminder.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.gabbm.ticket_office.reminder.util.Constants;

@Entity
@Table(name="Reminder")
public class Reminder {
	
	/*
	 * Standard fields
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private Constants.ReminderType type;
	
	@Column(name="order", nullable = false)
	private String order;
	
	@Column(name="to_email", nullable = false, length = Constants.EMAIL_MAX)
	private String toEmail;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> ccEmails = new HashSet<String>();
	
	@Column(name = "language", nullable = false)
	@Enumerated(EnumType.STRING)
	private Constants.Language language;
	
	@Column(name = "reminder_date")
	private LocalDateTime reminderDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Constants.ReminderType getType() {
		return type;
	}

	public void setType(Constants.ReminderType type) {
		this.type = type;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public Set<String> getCcEmails() {
		return ccEmails;
	}

	public Constants.Language getLanguage() {
		return language;
	}

	public void setLanguage(Constants.Language language) {
		this.language = language;
	}

	public LocalDateTime getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(LocalDateTime reminderDate) {
		this.reminderDate = reminderDate;
	}
}
