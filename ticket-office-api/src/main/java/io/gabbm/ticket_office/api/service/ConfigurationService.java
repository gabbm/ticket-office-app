package io.gabbm.ticket_office.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:ticketing-api-rest.properties")
public class ConfigurationService {

	/*
	 * Rest API configuration
	 */
	
	@Value("${ticketing.rest.api.context.path}")
	private String restContextPath;
	
	@Value("${ticketing.rest.api.jaxb.path}")
	private String restJaxbPath;
	
	@Value("${ticketing.rest.api.error.code}")
	private String restErrorCode;
	
	@Value("${ticketing.rest.api.error.code}")
	private String restUserPath;
	
	@Value("${ticketing.rest.api.error.user}")
	private String restUser;
	
	@Value("${ticketing.rest.api.error.pass}")
	private String restPass;
	
	@Value("${ticketing.rest.api.error.terminal}")
	private String restTerminal;
	
	@Value("${ticketing.rest.api.error.license}")
	private String restLicense;

	/*
	 * OAuth2 API configuration
	 */
	
	@Value("${ticketing.oauth.api.client.id}")
	private String oauthClientId;
	
	@Value("${ticketing.oauth.api.client.secret}")
	private String oauthClientSecret;

	@Value("${ticketing.oauth.api.username}")
	private String oauthUsername;
	
	@Value("${ticketing.oauth.api.password}")
	private String oauthPassword;
	
	@Value("${ticketing.oauth.api.base.url}")
	private String oauthBaseUrl;
	
	@Value("${ticketing.oauth.api.access.token}")
	private String oauthAccessToken;
	
	@Value("${ticketing.oauth.api.grant}")
	private String oauthGrant;
	
	@Value("${ticketing.oauth.api.scope}")
	private String oauthScope;
	
	public String getRestContextPath() {
		return restContextPath;
	}

	public String getRestJaxbPath() {
		return restJaxbPath;
	}

	public String getRestErrorCode() {
		return restErrorCode;
	}

	public String getRestUserPath() {
		return restUserPath;
	}

	public String getRestUser() {
		return restUser;
	}

	public String getRestPass() {
		return restPass;
	}

	public String getRestTerminal() {
		return restTerminal;
	}

	public String getRestLicense() {
		return restLicense;
	}

	public String getOauthClientId() {
		return oauthClientId;
	}

	public String getOauthClientSecret() {
		return oauthClientSecret;
	}

	public String getOauthUsername() {
		return oauthUsername;
	}

	public String getOauthPassword() {
		return oauthPassword;
	}

	public String getOauthBaseUrl() {
		return oauthBaseUrl;
	}

	public String getOauthAccessToken() {
		return oauthAccessToken;
	}

	public String getOauthGrant() {
		return oauthGrant;
	}

	public String getOauthScope() {
		return oauthScope;
	}
	
	
}
