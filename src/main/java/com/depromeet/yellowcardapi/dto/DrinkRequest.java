package com.depromeet.yellowcardapi.dto;

import com.depromeet.yellowcardapi.domain.Drink;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DrinkRequest {

    private Integer drinkId;
    private String name;

    @JsonProperty(value = "drink_type")
    private String drinkType;

    private String description;
    private Integer proof;
    private Integer price;

    public Drink toDrink() {
        return Drink.builder()
                .name(name)
                .drinkType(drinkType)
                .description(description)
                .proof(proof)
                .price(price)
                .build();
    }
}