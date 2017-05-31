package io.gabbm.ticket_office.reminder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
    private AuthenticationManager authenticationManager;
 
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
         endpoints.authenticationManager(authenticationManager);
    }
    
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
         // @formatter:off
         clients.inMemory()
	         	.withClient("third-party-rest")
	         	.secret("secret123")
	         	.scopes("read")
	         	.authorizedGrantTypes("authorization_code", "refresh_token", "password")
         	.and()
	         	.withClient("integration")
	         	.secret("secret123")
	      		.scopes("read", "write")
	      		.authorizedGrantTypes("authorization_code", "refresh_token", "password");
    } 
}
