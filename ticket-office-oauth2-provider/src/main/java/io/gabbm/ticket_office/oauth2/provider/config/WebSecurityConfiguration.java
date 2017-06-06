package io.gabbm.ticket_office.oauth2.provider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.gabbm.ticket_office.oauth2.provider.config.user.AccountAuthenticationProvider;
import io.gabbm.ticket_office.oauth2.provider.config.user.UserDetailsServiceImpl;

/*
 * 	So in before release 1.5.x OAuth2 resource server order was 3 which had higher priority then WebSecurityConfigurerAdapter.

	After release 1.5.x OAuth2 resource server order is set to SecurityProperties.ACCESS_OVERRIDE_ORDER - 1 (it is Integer.MAX_VALUE - 8 I think) which has now definitely lower priority then basic WebSecurityConfigurerAdapter order.

	That's why login page appears for me after migrate from 1.4.x to 1.5.x

	So, more elegant and java-like style solution is to set @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER) on WebSecurityConfigurerAdapter class
 */

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
    @Autowired
    private AccountAuthenticationProvider accountAuthenticationProvider;
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService);
		 auth.authenticationProvider(accountAuthenticationProvider);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
