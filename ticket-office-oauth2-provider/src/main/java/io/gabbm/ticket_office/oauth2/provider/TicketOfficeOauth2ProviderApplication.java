package io.gabbm.ticket_office.oauth2.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Example:
 curl -X POST -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=password123&username=username123&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
 curl http://localhost:8080/api/sample -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsicmVzdHNlcnZpY2UiXSwidXNlcl9uYW1lIjoicGFwaWRha29zIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTQ1Njc5NzQzNiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjNlYjA3MjIzLWY5ZTAtNGI0NS1hYTM3LTVjOGYzZDg1YTVkNCIsImNsaWVudF9pZCI6ImNsaWVudGFwcCJ9.oD3jbGe0o69mJmPlcoc9ALsLyME8hwOkn9_5TJxt3l0"
 */
@SpringBootApplication
public class TicketOfficeOauth2ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketOfficeOauth2ProviderApplication.class, args);
	}
}
