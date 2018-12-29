package com.depromeet.yellowcardapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
public class MainResponse {

    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "status_message")
    private String statusMessage;

    @JsonProperty(value = "profile_image_url")
    private String profileImageUrl;

    @JsonProperty(value = "drink_cards")
    private List<DrinkCardResponse> drinkCards;
}
