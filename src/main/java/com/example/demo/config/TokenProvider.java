package com.example.demo.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {


    public String getTokenValue(OAuth2Authentication authentication) {
        return ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();

    }
}
