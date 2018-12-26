package com.depromeet.yellowcardapi.dto;

import com.depromeet.yellowcardapi.domain.Drink;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class DrinkResponse {

    private Integer drinkId;
    private String name;
    private String drinkType;
    private String description;
    private Integer proof;
    private Integer price;
    private Integer numberOfLike;

    public static DrinkResponse from(Drink drink) {
        return DrinkResponse.builder()
                .drinkId(drink.getDrinkId())
                .name(drink.getName())
                .drinkType(drink.getDrinkType().name())
                .description(drink.getDescription())
                .proof(drink.getProof())
                .price(drink.getPrice())
                .numberOfLike(drink.getNumberOfLike())
                .build();
    }
}