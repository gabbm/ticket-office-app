package io.gabbm.ticket_office.util;

public class Constants {
	
	public static enum Language {
		es_ES, ca_ES, en_US, en_GB
	}
	
	public static enum ActivityType {
		ACTIVITY, EVENT
	}
	
	public static enum PricingType {
		PER_ASSISTANT, PER_GROUP
	}
	
	public static enum ReminderType {
		PAYMENT, PAYMENT_OVERDUE, VISIT
	}
	
	public static enum Role {
		WEBMASTER, REST, ADMIN
	}
	
	public static enum SessionType {
		ACTIVITY, EVENT
	}
	
	public static final int DESCRIPTION_MAX = 1024;
	public static final int EMAIL_MAX = 250;
	public static final int NAME_MAX = 120;
	public static final int PASSWORD_MAX = 120;
	
	public static final String EMAIL_PATTERN = "{A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	public static final String FILE_URL = "/file/";
}
