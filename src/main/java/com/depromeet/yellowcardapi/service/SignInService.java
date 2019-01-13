package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.SignInRequest;

public interface SignInService {

    String signIn(SignInRequest request);
}
