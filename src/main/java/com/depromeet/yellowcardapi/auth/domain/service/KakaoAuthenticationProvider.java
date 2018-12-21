package com.depromeet.yellowcardapi.auth.domain.service;

import com.depromeet.yellowcardapi.auth.domain.model.JwtToken;
import com.depromeet.yellowcardapi.auth.domain.model.KakaoUserMeResponse;
import com.depromeet.yellowcardapi.auth.exception.InvalidAccessTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

public class KakaoAuthenticationProvider implements AuthenticationProvider {

    private static final String KAKAO_USER_ME_URL = "https://kapi.kakao.com/v2/user/me";

    @Autowired
    private ExternalUserDetailsService userDetailsService;

    @Autowired
    private OkHttpClient httpClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String accessToken = authentication.getName();
        try {
            // TODO: access token 검증 처리
            KakaoUserMeResponse userMeResponse = kakaoAuthenticate(accessToken);
        } catch (IOException e) {
            throw new InvalidAccessTokenException();
        }

        return new UsernamePasswordAuthenticationToken(accessToken, null);
    }

    private KakaoUserMeResponse kakaoAuthenticate(String accessToken) throws IOException {
        Request userMeRequest = new Request.Builder()
                .url(KAKAO_USER_ME_URL)
                .addHeader("Authorization", JwtToken.PREFIX_BEARER_HEADER + " " + accessToken)
                .build();

        Response response = httpClient.newCall(userMeRequest).execute();
        return objectMapper.readValue(response.body().string(), KakaoUserMeResponse.class);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
