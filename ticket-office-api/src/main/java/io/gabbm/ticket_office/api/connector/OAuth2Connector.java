package io.gabbm.ticket_office.api.connector;

import java.util.Arrays;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import io.gabbm.ticket_office.api.service.ConfigurationService;

@Component
public class OAuth2Connector {
	private static Logger log = LoggerFactory.getLogger(OAuth2Connector.class);
	
	@Autowired
	private ConfigurationService configurationService;
	
	private static OAuth2RestTemplate restTemplate = null;
	private static RestTemplate restTemplatePatch = null;
	
	/**
	 * Method to create the connection with One Box API REST
	 */
	public void createConnection(int retries){
		try{
			restTemplate(true);
		}catch(Exception e){
			if(retries < 5){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					log.error(e.getMessage(), e);
				}
				
				createConnection(++retries);
			}else{
				log.error(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * Method to know if One Box ADM API is up or not
	 * @return
	 */
	public static boolean isServiceUp(){
		if(restTemplate != null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Method to load the REST connection with the One Box API
	 * @return
	 */
	private OAuth2RestTemplate restTemplate(boolean renew) {
        DefaultOAuth2ClientContext clientContext = null;
		
		if(renew || restTemplate == null){			
	        clientContext = new DefaultOAuth2ClientContext();
	        restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
		}
		
        //validateAccessToken(restTemplate);
        //restTemplate.setMessageConverters(Arrays.<HttpMessageConverter<?>> asList(new StringHttpMessageConverter()));

        return restTemplate;
    }
	
	/**
	 * Method to load the REST Patch connection with the One Box API
	 * @return
	 */
	public static RestTemplate restTemplatePatch() {
		HttpClient httpClient = null;

		if(restTemplatePatch == null){
			httpClient = HttpClients.createDefault();
	        restTemplatePatch = new RestTemplate();
	        restTemplatePatch.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
		}

        return restTemplatePatch;
    }
	
	/**
	 * Method to renew the token for the current connection
	 */
	public void renewToken(int repeat){
    	MultiValueMap<String, String> formData = null;
		HttpEntity<?> httpEntity = null;
		HttpHeaders requestHeaders = null;

        try{       	
        	requestHeaders = new HttpHeaders();
			requestHeaders.set("Authorization", "Basic " + 
						new String(Base64.encode((configurationService.getOauthClientId() + ":" + configurationService.getOauthClientSecret()).getBytes())));
			
        	formData = new LinkedMultiValueMap<String, String>();
        	formData.add("grant_type", "password");
        	formData.add("username", configurationService.getOauthUsername());
        	formData.add("password", configurationService.getOauthPassword());
        	formData.add("client_secret", configurationService.getOauthClientSecret());
        	//formData.add("refresh_token", restTemplate(false).getAccessToken().getRefreshToken().getValue());
            formData.add("scope", configurationService.getOauthScope());
        	
        	httpEntity = new HttpEntity<Object>(formData, requestHeaders);
        	
        	restTemplatePatch().exchange(configurationService.getOauthBaseUrl() + 
        			configurationService.getOauthAccessToken(),
					HttpMethod.POST, httpEntity, String.class);
        	
  	        restTemplate(true);        
        }catch(Exception e){
        	log.error(e.getMessage(), e);
        }
	}
	
	/**
	 * Method to configure the connection via REST
	 * 
	 * @return
	 */
    private ResourceOwnerPasswordResourceDetails resourceDetails() {
        ResourceOwnerPasswordResourceDetails resourceDetails = null;

        resourceDetails = new ResourceOwnerPasswordResourceDetails();
        
        resourceDetails.setUsername(configurationService.getOauthUsername());
        resourceDetails.setPassword(configurationService.getOauthPassword());
        resourceDetails.setAccessTokenUri(configurationService.getOauthBaseUrl()
        		+ configurationService.getOauthAccessToken());
        resourceDetails.setClientId(configurationService.getOauthClientId());
        resourceDetails.setClientSecret(configurationService.getOauthClientSecret());
        resourceDetails.setGrantType(configurationService.getOauthGrant());
        resourceDetails.setScope(Arrays.asList(configurationService.getOauthScope().split(",")));

        return resourceDetails;
    }
    
    /**
     * Method to validate the oAuth2 token when its fails
     * 
     * @param restTemplate
     */
    private String getAccessToken() throws Exception{
    	OAuth2AccessToken oAuth2AccessToken = null;
    	
    	oAuth2AccessToken = restTemplate(false).getAccessToken();    
    	
    	return oAuth2AccessToken.getValue();
    }
}
