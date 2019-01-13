package com.depromeet.yellowcardapi.controller;

import com.depromeet.yellowcardapi.dto.SignInRequest;
import com.depromeet.yellowcardapi.dto.SignInResponse;
import com.depromeet.yellowcardapi.service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignInController {

    private final SignInService signInService;

    @PostMapping("/signin")
    public SignInResponse signIn(@RequestBody SignInRequest request) {
        String token = signInService.signIn(request);
        return new SignInResponse(token);
    }
}
