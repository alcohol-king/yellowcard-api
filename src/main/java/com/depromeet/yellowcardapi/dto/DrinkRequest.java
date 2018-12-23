package com.depromeet.yellowcardapi.dto;

import com.depromeet.yellowcardapi.domain.Drink;
import lombok.Getter;

@Getter
public class DrinkRequest {
    private Integer drink_id;
    private String name;
    private String drink_type;
    private String description;
    private Integer proof;
    private Integer price;
    private Integer number_of_like;

    public Drink toDrink() {
        return Drink.builder()
                .drink_id(drink_id)
                .name(name)
                .drink_type(drink_type)
                .description(description)
                .proof(proof)
                .price(price)
                .number_of_like(number_of_like)
                .build();
    }
}