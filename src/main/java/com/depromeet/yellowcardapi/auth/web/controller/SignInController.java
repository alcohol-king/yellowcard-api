package com.depromeet.yellowcardapi.auth.web.controller;

import com.depromeet.yellowcardapi.auth.web.model.SignInRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    @PostMapping("/signin")
    public void signIn(@RequestBody SignInRequest request) {

    }
}
