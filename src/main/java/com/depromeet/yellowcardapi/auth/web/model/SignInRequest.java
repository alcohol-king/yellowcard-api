package com.depromeet.yellowcardapi.auth.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignInRequest {

    private String accessToken;
    private String userId;
}
