package io.gabbm.ticket_office.oauth2.provider.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import io.gabbm.ticket_office.oauth2.provider.config.user.UserDetailsServiceImpl;

/**
 * OAuth2 provider configuration with JDBC token management
 */
@Configuration
public class OAuth2ServerConfiguration {

	/*
	 * Define RESOURCE IDs to manage with OAuth2 provider
	 */
	private static final String RESOURCE_ID = "restservice";

	/**
	 * Resource server configuration for each resource
	 */
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends
			ResourceServerConfigurerAdapter {

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources.resourceId(RESOURCE_ID);
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
			 	.requestMatchers().antMatchers("/api/**")
			 .and()
			 	.authorizeRequests()
			  		.antMatchers("/api/admin/**").access("#oauth2.hasScope('write') or (!#oauth2.isOAuth() and hasRole('ADMIN'))")
			  		.antMatchers("/api/open/**").access("#oauth2.hasScope('read')")
			 .and()
		     	.csrf()
		     		.csrfTokenRepository(csrfTokenRepository()).and()
		            .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
		}
		
		/*
		 * Manage Cross Site requests for better performance
		 */
		private Filter csrfHeaderFilter() {
            return new OncePerRequestFilter() {

                @Override
                protected void doFilterInternal(HttpServletRequest request,
                        HttpServletResponse response, FilterChain filterChain)
                        throws ServletException, IOException {

                    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                            .getName());
                     if (csrf != null) {
                        Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                        String token = csrf.getToken();
                        if (cookie == null || token != null
                                && !token.equals(cookie.getValue())) {
                            cookie = new Cookie("XSRF-TOKEN", token);
                            cookie.setPath("/");
                            response.addCookie(cookie);
                        }
                    }
                    filterChain.doFilter(request, response);
                }
            };
        }
		
        private CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }

	}
	
	/**
	 * Define Token Store management
	 */
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends
			AuthorizationServerConfigurerAdapter {
		
		private TokenStore tokenStore;
		
		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Autowired
        DataSource dataSource;
		
		/*
	     * A Spring Security UserDetailsService implementation based upon the
	     * User entity model.
	     */
		@Autowired
		private UserDetailsServiceImpl userDetailsService;
		
		
		/*
		 * Configure the token store, also the authentication manager and the user repository 
		 */
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			endpoints
				.tokenStore(tokenStore())
				.authenticationManager(this.authenticationManager)
				.userDetailsService(userDetailsService)
				.authorizationCodeServices(authorizationCodeServices())
				.approvalStoreDisabled();
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.jdbc(dataSource);
		}
		
		@Bean
        public TokenStore tokenStore() {
			if(tokenStore == null){
				tokenStore = new JdbcTokenStore(dataSource);
			}
			
			return tokenStore;
        }
		
        @Bean
        protected AuthorizationCodeServices authorizationCodeServices() {
        	return new JdbcAuthorizationCodeServices(dataSource);
        }
        
        /*
         * Set Token with refresh, 5 minutes of Access Token validity and 100 minutes of Refresh Token validity
         */
		@Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setAccessTokenValiditySeconds(300);
            tokenServices.setRefreshTokenValiditySeconds(6000);
            tokenServices.setTokenStore(tokenStore());
            return tokenServices;
        }

	}

}
