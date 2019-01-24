package com.depromeet.yellowcardapi.dto;

import com.depromeet.yellowcardapi.domain.DrinkCard;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreateDrinkCardRequest {

    @JsonProperty(value = "drink_type")
    private String drinkType;

    private String message;

    public DrinkCard toDrinkCard() {
        return DrinkCard.builder()
                .build();
    }
}
