package io.gabbm.ticket_office.reminder.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import io.gabbm.ticket_office.reminder.util.Constants;

@Entity
@Table(name="ReminderTask")
public class ReminderTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private Constants.ReminderType type;
	
	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	// Reminder date calculated from order day (true) or visit day (false)
	@Column(name = "order_day", nullable = false)
	private boolean orderDay = false;
	
	// Days to sum (order day) or subtract (visit day)
	@Column(name = "gap_days", nullable = false)
	private int gapDays = 5;
	
	@Column(name = "template", nullable = false)
	private String template;
	
	@ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="custom_attributes", joinColumns=@JoinColumn(name="id"))
    private Map<String, String> attributes = new HashMap<String, String>();
	
	@Column(name = "remove", nullable = false)
	private boolean removeAfterReminder = true;
	
	/*
	 * Custom fields
	 */
	// Museum, null value is equals to all centers
	@Column(name = "center_id")
	private String centerId;

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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public boolean isOrderDay() {
		return orderDay;
	}

	public void setOrderDay(boolean orderDay) {
		this.orderDay = orderDay;
	}

	public int getGapDays() {
		return gapDays;
	}

	public void setGapDays(int gapDays) {
		this.gapDays = gapDays;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public boolean isRemoveAfterReminder() {
		return removeAfterReminder;
	}

	public void setRemoveAfterReminder(boolean removeAfterReminder) {
		this.removeAfterReminder = removeAfterReminder;
	}

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	
}
