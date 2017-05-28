package io.gabbm.ticket_office.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketOfficeApiApplication {

	public static void main(String[] args) {
		/*
		 * Configures application and return the context of the application (Beans, etc.)
		 */
		SpringApplication.run(TicketOfficeApiApplication.class, args);
	}
}
