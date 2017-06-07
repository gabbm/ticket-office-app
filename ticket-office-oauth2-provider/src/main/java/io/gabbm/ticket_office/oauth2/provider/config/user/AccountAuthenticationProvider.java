package io.gabbm.ticket_office.oauth2.provider.config.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Custom authentication validations with custom password encoder
 */
@Component
public class AccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    /*
     * A Spring Security UserDetailsService implementation based upon the
     * User entity model.
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /*
     * A PasswordEncoder instance to hash clear test password values.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * Custom authentication checks
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
        if (token.getCredentials() == null || userDetails.getPassword() == null) {
            throw new BadCredentialsException("Credentials may not be null.");
        }

        if (!passwordEncoder.matches((String) token.getCredentials(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials.");
        }
    }

    /*
     * Custom user provider from repository
     */
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return userDetails;
    }

}

