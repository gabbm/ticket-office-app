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
	
	public static final String MAGIC_DOUBLE_DOT_STRING = ":";
	public static final String MAGIC_HTTP_STRING = "http://";
	public static final String MAGIC_END_BRACKET_STRING = "]";
	public static final String MAGIC_START_BRACKET_STRING = "[";
	public static final String MAGIC_TO_STRING = " TO ";
	
	public final class TicketingApiRest {		
		public static final String MAGIC_AND_STRING = "AND";
		public static final String MAGIC_DATE_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		public static final String MAGIC_DATE_FORMAT_JODA_STRING = "yyyy-MM-dd'T'HH:mm";
		public static final String MAGIC_DATE_FORMAT_DB_STRING = "yyyy-MM-dd HH:mm";
		public static final String MAGIC_DATE_FORMAT_BASIC_STRING = "yyyy-MM-dd";
		public static final String MAGIC_DATE_FORMAT_JODA_OFFSET_STRING = "ZZ";
		public static final String MAGIC_DATE_OLSON_ID_STRING = "Europe/Berlin";
		public static final String MAGIC_DATE_FORMAT_SIMPLE_STRING = "dd/MM/yyyy";
		public static final String MAGIC_DATE_FORMAT_TIME_STRING = "HH:mm";
		public static final String MAGIC_DATE_FORMAT_FORM_STRING = "yyyy-MM-dd";

		public static final String MAGIC_ERROR_CODE_STRING = "OB_Error_Code";

		public static final String MAGIC_HTTP_PROXY_PASSWORD_STRING = "http.proxyPassword";
		public static final String MAGIC_HTTP_PROXY_USER_STRING = "http.proxyUser";



		public static final String PROPERTIES_ENTITY_ID = "onebox.entity.id";
		
		public static final String PROPERTIES_HOST = "onebox.host";
		public static final String PROPERTIES_LICENSE = "onebox.license";
		public static final String PROPERTIES_MD5_PASS = "onebox.md5.pass";
		public static final String PROPERTIES_TERMINAL = "onebox.terminal";
		public static final String PROPERTIES_USER = "onebox.user";
		
		public static final String PROPERTIES_OAUTH_BASEURL = "onebox.oauth2.baseUrl";
		public static final String PROPERTIES_OAUTH_RESTURL = "onebox.oauth2.restUrl";
		public static final String PROPERTIES_OAUTH_ACCESSTOKEN = "onebox.oauth2.accessToken";
		public static final String PROPERTIES_OAUTH_CLIENTID = "onebox.oauth2.clientId";
		public static final String PROPERTIES_OAUTH_CLIENTSECRET = "onebox.oauth2.clientSecret";
		public static final String PROPERTIES_OAUTH_USERNAME = "onebox.oauth2.username";
		public static final String PROPERTIES_OAUTH_PASSWORD = "onebox.oauth2.password";
		
		public static final String PROPERTIES_CONTEXT_PATH = "onebox.context.path";

		public static final String PROPERTIES_PROXY_HOST = "onebox.proxy.host";
		public static final String PROPERTIES_PROXY_PORT = "onebox.proxy.port";		
	}
}
