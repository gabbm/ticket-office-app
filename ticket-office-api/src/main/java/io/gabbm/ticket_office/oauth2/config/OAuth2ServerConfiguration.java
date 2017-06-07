package io.gabbm.ticket_office.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import io.gabbm.ticket_office.oauth2.provider.service.impl.UserDetailsServiceImpl;

@Configuration
public class OAuth2ServerConfiguration {

	private static final String TICKET_API_RESOURCE_ID = "ticketapi";

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends
			ResourceServerConfigurerAdapter {

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			// @formatter:off
			resources.resourceId(TICKET_API_RESOURCE_ID);
			// @formatter:on
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
			// Since we want the protected resources to be accessible in the UI as well we need 
			// session creation to be allowed (it's disabled by default in 2.0.6)
			//	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			//.and()
			//	.requestMatchers().antMatchers("/api/**")
			//.and()
				.authorizeRequests()      
					//.antMatchers("/oauth/tokent").permitAll()
					.antMatchers("/api/admin/**").hasRole("ADMIN")
					.antMatchers("/api/open/**").authenticated();
					//.antMatchers("/api/admin/**").access("#oauth2.hasScope('write') or (!#oauth2.isOAuth() and hasRole('ADMIN'))")
					//.antMatchers("/api/open/**").access("#oauth2.hasScope('read')");
		}
		
	}
	
	
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends
			AuthorizationServerConfigurerAdapter {

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Autowired
        DataSource dataSource;
		
		@Autowired
		private UserDetailsServiceImpl userDetailsService;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			endpoints.userDetailsService(userDetailsService)
					.authorizationCodeServices(authorizationCodeServices())
					.authenticationManager(this.authenticationManager)
					.tokenStore(tokenStore())
					.approvalStoreDisabled();
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// @formatter:off
			clients.jdbc(dataSource);
			// @formatter:on
		}

		@Bean
        public JdbcTokenStore tokenStore() {
			return new JdbcTokenStore(dataSource);
        }
		
        @Bean
        protected AuthorizationCodeServices authorizationCodeServices() {
        	return new JdbcAuthorizationCodeServices(dataSource);
        }
		
		@Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setAccessTokenValiditySeconds(300);
            tokenServices.setRefreshTokenValiditySeconds(6000);
            tokenServices.setTokenStore(new JdbcTokenStore(dataSource));
            return tokenServices;
        }

	}
	
	/*
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends
			AuthorizationServerConfigurerAdapter {

		private TokenStore tokenStore = new InMemoryTokenStore();

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Autowired
		private UserDetailsServiceImpl userDetailsService;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			// @formatter:off
			endpoints
				.tokenStore(this.tokenStore)
				.authenticationManager(this.authenticationManager)
				.userDetailsService(userDetailsService);
			// @formatter:on
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// @formatter:off
			clients
				.inMemory()
					.withClient("test")
						.authorizedGrantTypes("password", "refresh_token")
						.authorities("USER")
						.scopes("read", "write")
						.resourceIds(TICKET_API_RESOURCE_ID)
						.secret("test1234");
			// @formatter:on
		}

		@Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			DefaultTokenServices tokenServices = new DefaultTokenServices();
			tokenServices.setSupportRefreshToken(true);
			tokenServices.setTokenStore(this.tokenStore);
			return tokenServices;
		}

	}
	
	*/
}