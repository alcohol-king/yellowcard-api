package com.depromeet.yellowcardapi.dto;

import com.depromeet.yellowcardapi.domain.Drink;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class DrinkResponse {
    private Integer drink_id;
    private String name;
    private String drink_type;
    private String description;
    private Integer proof;
    private Integer price;
    private Integer number_of_like;

    public static DrinkResponse from(Drink drink) {
        return DrinkResponse.builder()
                .drink_id(drink.getDrink_id())
                .name(drink.getName())
                .drink_type(drink.getDrink_type())
                .description(drink.getDescription())
                .proof(drink.getProof())
                .price(drink.getPrice())
                .number_of_like(drink.getNumber_of_like())
                .build();
    }
}