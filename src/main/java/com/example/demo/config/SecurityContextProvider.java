package com.example.demo.config;

import com.example.demo.model.Authority;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SecurityContextProvider {


    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;

    @Autowired
    private TokenProvider tokenProvider;

    @SuppressWarnings("unchecked")
    public User getByAuthentication(OAuth2Authentication oAuth2Authentication) {

        Map<String, Object> additional = resourceServerTokenServices.readAccessToken(tokenProvider.getTokenValue(oAuth2Authentication)).getAdditionalInformation();

        User user = new User();
        user.setId((Integer) additional.get("userId"));
        user.setUsername(additional.get("username").toString());
        user.setName(additional.get("name").toString());

        HashMap<Integer, String> roles = (HashMap<Integer, String>) additional.get("roles");
        List<Authority> authorities = new ArrayList<>();
        roles.forEach((key, value) -> authorities.add(new Authority(key, value)));
        user.setAuthorities(authorities);

        return user;
    }

    public String readTokenValue(OAuth2Authentication auth2Authentication) {
        return tokenProvider.getTokenValue(auth2Authentication);
    }
}
