package io.gabbm.ticket_office.api.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "restservice";

    @Override
	public void configure(ResourceServerSecurityConfigurer resources) {
    	RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setClientId("clientapp");
        tokenService.setClientSecret("123456");
        tokenService.setCheckTokenEndpointUrl("http://localhost:8080/ticket-office-oauth2/oauth/check_token");

        resources.resourceId(RESOURCE_ID).tokenServices(tokenService);
	}

	/*
	 * Since we want the protected resources to be accessible in the UI as well we need 
	 * session creation to be allowed (it's disabled by default in 2.0.6)
	 * sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	 * 
	 * Enabling security for /api path
	 * requestMatchers().antMatchers("/api/**")
	 * 
	 * Enabling access if is logged and has scope X or has Role Y
	 * .authorizeRequests()
	 * 		.antMatchers("/api/admin/**").access("#oauth2.hasScope('write') or (!#oauth2.isOAuth() and hasRole('ADMIN'))")
	 * 		.antMatchers("/api/open/**").access("#oauth2.hasScope('read')");
	 * 
	 * Also we can use 
	 * .authorizeRequests()
	 * 		.antMatchers("/api/admin/**").hasRole("ADMIN")
	 * 		.antMatchers("/api/open/**").authenticated()
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		 	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		 .and()
		 	.requestMatchers()
		 		.antMatchers("/api/admin/**")
		 		.antMatchers("/api/open/**")
		 .and()
		 	.authorizeRequests()
		  		.antMatchers("/api/admin/**").access("#oauth2.hasScope('write') or (!#oauth2.isOAuth() and hasRole('ADMIN'))")
		  		.antMatchers("/api/open/**").access("#oauth2.hasScope('read')")
		 .and()
	     	.csrf().disable();
	}
}
