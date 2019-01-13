package com.depromeet.yellowcardapi.dto;

import com.depromeet.yellowcardapi.domain.DrinkCard;
import com.depromeet.yellowcardapi.domain.DrinkType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class DrinkLabelResponse {

    @JsonProperty(value = "drink_type")
    private String drinkType;

    private double level;

    public static List<DrinkLabelResponse> from(List<DrinkCard> drinkCards) {
        List<DrinkLabelResponse> drinkLabelResponses = new ArrayList<>();
        for (DrinkCard drinkCard : drinkCards) {
            DrinkLabelResponse drinkLabelResponse = DrinkLabelResponse.builder()
                    .drinkType(drinkCard.getDrinkType())
                    .level(drinkCard.getLevel())
                    .build();
            drinkLabelResponses.add(drinkLabelResponse);
        }

        return drinkLabelResponses;
    }

    public static class DrinkLabelResponseBuilder {
        public DrinkLabelResponseBuilder drinkType(String drinkType) {
            this.drinkType = drinkType;
            return this;
        }

        public DrinkLabelResponseBuilder drinkType(DrinkType drinkType) {
            this.drinkType = drinkType.name();
            return this;
        }
    }
}
