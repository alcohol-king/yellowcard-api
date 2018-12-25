package com.depromeet.yellowcardapi.dto;

import com.depromeet.yellowcardapi.domain.Drink;
import lombok.Getter;

@Getter
public class DrinkRequest {

    private Integer drinkId;
    private String name;
    private String drinkType;
    private String description;
    private Integer proof;
    private Integer price;
    private Integer numberOfLike;

    public Drink toDrink() {
        return Drink.builder()
                .drinkId(drinkId)
                .name(name)
                .drinkType(drinkType)
                .description(description)
                .proof(proof)
                .price(price)
                .numberOfLike(numberOfLike)
                .build();
    }
}