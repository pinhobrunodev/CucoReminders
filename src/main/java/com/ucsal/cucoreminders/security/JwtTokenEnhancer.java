package com.ucsal.cucoreminders.security;

import java.util.HashMap;
import java.util.Map;

import com.ucsal.cucoreminders.entities.User;
import com.ucsal.cucoreminders.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        User user = userRepository.findByFullName(authentication.getName());

        Map<String, Object> additionalInfo = new HashMap<>();

        additionalInfo.put("userId", user.getId());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;

        token.setAdditionalInformation(additionalInfo);

        return accessToken;
    }

}

