package com.depromeet.yellowcardapi.dto;

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

    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "status_message")
    private String statusMessage;
}
