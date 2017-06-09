package io.gabbm.ticket_office.api.connector;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;

import es.onebox.rest.filters.HMACSHA1ClientFilter;
import es.oneboxtm.user.User;
import io.gabbm.ticket_office.api.service.ConfigurationService;

@Component
public class RestConnector {
	private static Logger log = LoggerFactory.getLogger(RestConnector.class);

	private static User user = null;

	@Autowired
	private ConfigurationService configurationService;
	
	/**
	 * Method to get the Apache HTTP Client to establish the connection with OneBox
	 * 
	 * @return
	 */
	private static Client getClient() {
		Client client = null;

		try {
			client = ApacheHttpClient.create(new DefaultApacheHttpClientConfig());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return client;
	}
	
	/**
	 * Method to load the response for current REST call filtered by Query
	 * Parameters and/or Form
	 * 
	 * @param
	 * @param filterWithoutChannel
	 * @param sendType:
	 * 			001 - GET
	 * 			002 - POST
	 * 			003 - PUT
	 * @param form
	 * @param channelId
	 * @param queryParams
	 * @param restPath
	 * @return
	 */
	public ClientResponse getClientResponse(boolean cache, Integer sendType,
			Form form, Integer channelId, Map<String, String> queryParams,
			String restPath, String acceptLanguage) {
		Client client = null;
		ClientResponse clientResponse = null;
		WebResource webResource = null;

		client = getClient();
		
		webResource = client.resource(configurationService.getRestContextPath() + restPath);
		webResource.addFilter(getHMACSHA1ClientFilter(channelId));

		if (queryParams != null) {
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
				webResource = webResource.queryParam(entry.getKey(),
						entry.getValue());
			}
		}

		if(sendType == 001){			
			if (cache) {
				if (StringUtils.isNotBlank(acceptLanguage)) {
					clientResponse = webResource
							.header("Cache-Control", "no-cache")
							.header("expires", "0")
							.header("Accept-Language", acceptLanguage)
							.type(MediaType.APPLICATION_XML)
							.get(ClientResponse.class);
				} else {
					clientResponse = webResource
							.header("Cache-Control", "no-cache")
							.header("expires", "0").type(MediaType.APPLICATION_XML)
							.get(ClientResponse.class);
				}
			} else {
				if (StringUtils.isNotBlank(acceptLanguage)) {
					clientResponse = webResource.header("Accept-Language",
							acceptLanguage).type(MediaType.APPLICATION_XML)
							.get(ClientResponse.class);
				} else {
					clientResponse = webResource.type(MediaType.APPLICATION_XML)
							.get(ClientResponse.class);
				}
			}
		} else if (sendType == 002) {			
			if (form != null) {
				if (cache) {
					if (StringUtils.isNotBlank(acceptLanguage)) {
						clientResponse = webResource
								.header("Cache-Control", "no-cache")
								.header("expires", "0")
								.header("Accept-Language", acceptLanguage)
								.type(MediaType.APPLICATION_XML)
								.post(ClientResponse.class, form);
					} else {
						clientResponse = webResource
								.header("Cache-Control", "no-cache")
								.header("expires", "0")
								.type(MediaType.APPLICATION_XML)
								.post(ClientResponse.class, form);
					}
				} else {
					if (StringUtils.isNotBlank(acceptLanguage)) {
						clientResponse = webResource.header("Accept-Language",
								acceptLanguage)
								.type(MediaType.APPLICATION_XML)
								.post(ClientResponse.class, form);
					} else {
						clientResponse = webResource.type(MediaType.APPLICATION_XML)
								.post(ClientResponse.class,
								form);
					}
				}
			} else {
				if (StringUtils.isNotBlank(acceptLanguage)) {
					clientResponse = webResource.header("Accept-Language",
							acceptLanguage).type(MediaType.APPLICATION_XML)
							.post(ClientResponse.class);
				} else {
					clientResponse = webResource.type(MediaType.APPLICATION_XML)
							.post(ClientResponse.class);
				}
			}
		} else {			
			if (cache) {
				if (StringUtils.isNotBlank(acceptLanguage)) {
					clientResponse = webResource
							.header("Cache-Control", "no-cache")
							.header("expires", "0")
							.header("Accept-Language", acceptLanguage)
							.type(MediaType.APPLICATION_XML)
							.put(ClientResponse.class);
				} else {
					clientResponse = webResource
							.header("Cache-Control", "no-cache")
							.header("expires", "0")
							.type(MediaType.APPLICATION_XML)
							.put(ClientResponse.class);
				}
			} else {
				if (StringUtils.isNotBlank(acceptLanguage)) {
					clientResponse = webResource.header("Accept-Language",
							acceptLanguage).type(MediaType.APPLICATION_XML)
							.put(ClientResponse.class);
				} else {
					clientResponse = webResource.type(MediaType.APPLICATION_XML)
							.put(ClientResponse.class);
				}
			}
		}
		
		return clientResponse;
	}
	
	/**
	 * Method to handle the response of current REST call and shows the modal
	 * 
	 * @param response
	 * @return
	 * @throws EventNotAvailableException
	 * @throws EventCancelledException
	 * @throws EventEndedException
	 * @throws SessionNotAvailableException
	 * @throws SessionNotForSaleException
	 * @throws BookingNotPermittedException
	 * @throws BasicIncompatibilityEngineException
	 */
	private static String handleResponse(ClientResponse response) 
			throws IOException{
		MultivaluedMap<String, String> map = null;
		String errorCode = null;

		map = response.getHeaders();
		errorCode = map.getFirst(errorCode);
		
		if (response.getStatus() != 200) {
			if (errorCode != null) {
				switch (errorCode) {
				default:
					log.error("Error in Ticketing Rest call :: " + errorCode);
					throw new IOException("Error in Ticketing Rest call :: " + errorCode);
				}
			}
		}
		return errorCode;
	}
	
	/**
	 * Method to validate the connection user
	 * 
	 * @return
	 * @throws EventNotAvailableException
	 * @throws EventCancelledException
	 * @throws EventEndedException
	 * @throws SessionNotAvailableException
	 * @throws SessionNotForSaleException
	 * @throws BasicIncompatibilityEngineException
	 * @throws BookingNotPermittedException
	 */
	public User getUser() {
		ClientResponse responseSearch = null;

		try {
			if(user == null){
				responseSearch = getClientResponse(false,
						001,
						null,
						null,
						null,
						configurationService.getRestUserPath(),
						null);
				if (handleResponse(responseSearch) == null) {
					user = responseSearch.getEntity(User.class);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return user;
	}
	
	/**
	 * Method to get the marshaller defined for mapping XML REST Response and
	 * their Java Objects
	 * 
	 * @return
	 */
	public Jaxb2Marshaller getJaxb2Marshaller() {
		Jaxb2Marshaller jaxbMarshallerCamel = null;
		
		try {
			jaxbMarshallerCamel = new Jaxb2Marshaller();
			jaxbMarshallerCamel.setContextPath(configurationService.getRestJaxbPath());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return jaxbMarshallerCamel;
	}
	
	
	/*
	 * CUSTOM METHODS
	 */
	
	/**
	 * Method to configure the connection filter encoded with HMAC-SHA1 with
	 * default channel
	 * 
	 * @param channelId
	 * @return
	 */
	private HMACSHA1ClientFilter getHMACSHA1ClientFilter(
			Integer channelId) {
		HMACSHA1ClientFilter hmacFilterClientFilter = null;

		try {
			hmacFilterClientFilter = new HMACSHA1ClientFilter(
					configurationService.getRestUser(),
					configurationService.getRestPass(),
					configurationService.getRestTerminal(),
					configurationService.getRestLicense(),
					channelId != null ? channelId : null, 
					getPointOfSaleId());
			hmacFilterClientFilter.setChannel(channelId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return hmacFilterClientFilter;
	}	
	
	/**
	 * Method to get the point of sale of the current user connection
	 * 
	 * @return
	 */
	private int getPointOfSaleId() throws Exception {
		int pointOfSaleId = 0;

		pointOfSaleId = getUser().getEntity().getPointsOfSales()
				.getPointOfSales().get(0).getId().intValue();

		return pointOfSaleId;
	}
}
