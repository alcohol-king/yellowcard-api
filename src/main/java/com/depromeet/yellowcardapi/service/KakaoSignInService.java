package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.User;
import com.depromeet.yellowcardapi.domain.UserRepository;
import com.depromeet.yellowcardapi.dto.KakaoUserMeResponse;
import com.depromeet.yellowcardapi.dto.SignInRequest;
import com.depromeet.yellowcardapi.exception.KakaoAuthenticationFailedException;
import com.depromeet.yellowcardapi.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KakaoSignInService implements SignInService {

    public static final String KAKAO_USER_ME_URL = "https://kapi.kakao.com/v2/user/me";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private OkHttpClient httpClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String signIn(SignInRequest request) {
        KakaoUserMeResponse userMeResponse = new KakaoUserMeResponse();
        try {
            userMeResponse = authenticate(request.getAccessToken());
        } catch (IOException e) {
            e.printStackTrace();
            throw new KakaoAuthenticationFailedException();
        }

        Long externalId = userMeResponse.getId();
        User user = userRepository.findByExternalId(externalId)
                .orElseGet(() -> {
                    if (StringUtils.isBlank(request.getUserName())) {
                        throw new UserNotFoundException();
                    }
                    return userRepository.save(User.builder()
                            .externalId(externalId)
                            .name(request.getUserName())
                            .statusMessage(request.getStatusMessage())
                            .build());
                });

        return tokenProvider.generateToken(user);
    }

    private KakaoUserMeResponse authenticate(String accessToken) throws IOException {
        Request userMeRequest = new Request.Builder()
                .url(KAKAO_USER_ME_URL)
                .addHeader("Authorization", JwtTokenProvider.PREFIX_TOKEN + " " + accessToken)
                .build();

        Response response = httpClient.newCall(userMeRequest).execute();

        if (HttpStatus.valueOf(response.code()) != HttpStatus.OK) {
            throw new KakaoAuthenticationFailedException("카카오 인증 토큰이 유효하지 않습니다.");
        }

        return objectMapper.readValue(response.body().string(), KakaoUserMeResponse.class);
    }
}
