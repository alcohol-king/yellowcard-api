package com.depromeet.yellowcardapi.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignInRequest {

    @JsonProperty(value = "access_token")
    private String accessToken;
}
