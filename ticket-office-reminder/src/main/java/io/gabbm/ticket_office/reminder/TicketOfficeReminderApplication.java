package io.gabbm.ticket_office.reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

//for jsr310 java 8 java.time.*
@EntityScan(
      basePackageClasses = {TicketOfficeReminderApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class TicketOfficeReminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketOfficeReminderApplication.class, args);
	}
}
