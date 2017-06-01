package io.gabbm.ticket_office.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Example:
 curl -X POST -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=password123&username=username123&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
 curl http://localhost:8080/api/sample -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsicmVzdHNlcnZpY2UiXSwidXNlcl9uYW1lIjoicGFwaWRha29zIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTQ1Njc5NzQzNiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjNlYjA3MjIzLWY5ZTAtNGI0NS1hYTM3LTVjOGYzZDg1YTVkNCIsImNsaWVudF9pZCI6ImNsaWVudGFwcCJ9.oD3jbGe0o69mJmPlcoc9ALsLyME8hwOkn9_5TJxt3l0"
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableAsync
public class TicketOfficeOauth2Application {

    /**
     * Entry point for the application.
     *
     * @param args Command line arguments.
     * @throws Exception Thrown when an unexpected Exception is thrown from the
     *         application.
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TicketOfficeOauth2Application.class, args);
    }

    /**
     * Create a CacheManager implementation class to be used by Spring where
     * <code>@Cacheable</code> annotations are applied.
     *
     * @return A CacheManager instance.
     */
    @Bean
    public CacheManager cacheManager() {

        GuavaCacheManager cacheManager = new GuavaCacheManager("ticket_office");

        return cacheManager;
    }


}
