package io.gabbm.ticket_office.oauth2.provider.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.gabbm.ticket_office.oauth2.provider.entity.Users;
import io.gabbm.ticket_office.oauth2.provider.model.Greeting;

@RestController
@RequestMapping("/api/open")
public class GreetingController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();

	/*
	 * @AuthenticationPrincipal brings current authenticated user
	 */
	@RequestMapping("/greeting")
	public Greeting greeting(@AuthenticationPrincipal Users user) {
		return new Greeting(counter.incrementAndGet(),
				String.format(template, user.getName()));
	}

}
