package com.manage_film_example.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        AuthenticationService demoAuthentication = (AuthenticationService) authentication;       
        
        return demoAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthenticationService.class.isAssignableFrom(authentication);
    }

}