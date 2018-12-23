package com.depromeet.yellowcardapi.user.service;

import com.depromeet.yellowcardapi.user.domain.User;
import com.depromeet.yellowcardapi.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new UsernameNotFoundException("일치하는 사용자 정보가 존재하지 않습니다."));

        return new org.springframework.security.core.userdetails.User(user.getId().toString(), "",
                Collections.emptyList());
    }
}
