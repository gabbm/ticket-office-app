package io.gabbm.ticket_office.api.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication();
    }
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	    	.authorizeRequests()
    			.antMatchers("/swagger-ui.html").permitAll()
	    		.antMatchers("/swagger.json").permitAll()
	    		.antMatchers("/swagger-resources/**").permitAll()
	    		.antMatchers("/webjars/**").permitAll()
	        	.anyRequest().authenticated()
	        .and()
	        	.exceptionHandling()
	            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/swagger-ui.html"))
	        .and()
	        	.formLogin().disable();
	}
}
