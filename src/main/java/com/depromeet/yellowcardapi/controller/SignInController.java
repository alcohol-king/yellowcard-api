package com.depromeet.yellowcardapi.controller;

import com.depromeet.yellowcardapi.dto.SignInRequest;
import com.depromeet.yellowcardapi.dto.SignInResponse;
import com.depromeet.yellowcardapi.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/signin")
    public SignInResponse signIn(@RequestBody SignInRequest request) {
        String token = signInService.signIn(request.getAccessToken());
        return new SignInResponse(token);
    }
}
