package com.depromeet.yellowcardapi.dto;

import com.depromeet.yellowcardapi.domain.DrinkCard;
import com.depromeet.yellowcardapi.domain.DrinkType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class DrinkCardResponse {

    @JsonProperty(value = "drink_type")
    private String drinkType;

    private String message;

    public static List<DrinkCardResponse> from(List<DrinkCard> drinkCards) {
        List<DrinkCardResponse> drinkCardResponses = new ArrayList<>();
        for (DrinkCard drinkCard : drinkCards) {
            DrinkCardResponse drinkCardResponse = DrinkCardResponse.builder()
                    .drinkType(drinkCard.getDrinkType())
                    .message(drinkCard.getMessage())
                    .build();
            drinkCardResponses.add(drinkCardResponse);
        }

        return drinkCardResponses;
    }

    public static class DrinkCardResponseBuilder {
        public DrinkCardResponseBuilder drinkType(String drinkType) {
            this.drinkType = drinkType;
            return this;
        }

        public DrinkCardResponseBuilder drinkType(DrinkType drinkType) {
            this.drinkType = drinkType.name();
            return this;
        }
    }
}