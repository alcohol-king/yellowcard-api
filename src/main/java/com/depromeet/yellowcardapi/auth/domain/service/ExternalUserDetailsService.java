package com.depromeet.yellowcardapi.auth.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ExternalUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String kakaoAccessToken) throws UsernameNotFoundException {
        return new User(kakaoAccessToken, "", new HashSet<>());
    }
}
