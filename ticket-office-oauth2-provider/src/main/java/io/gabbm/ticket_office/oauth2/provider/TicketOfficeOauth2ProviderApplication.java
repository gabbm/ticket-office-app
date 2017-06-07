package io.gabbm.ticket_office.oauth2.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Example:
 curl -X POST -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=$2y$10$kIlXr3np0JN8Ho3p47heM.pKFC5T9y.1fs6OISTqYwgTyKLQ2m5aO&username=roy&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
 curl http://localhost:8080/greeting -H "Authorization: Bearer dae022d1-8a24-4522-bb29-4224df138881"
 */
@SpringBootApplication
public class TicketOfficeOauth2ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketOfficeOauth2ProviderApplication.class, args);
	}
}
