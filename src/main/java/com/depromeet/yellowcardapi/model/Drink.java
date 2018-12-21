package com.depromeet.yellowcardapi.model;

import com.depromeet.yellowcardapi.entity.DrinkEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Drink {
    private Integer drink_id;
    private String name;
    private String drink_type;
    private String description;
    private Integer proof;
    private Integer price;
    private Integer number_of_like;

    public DrinkEntity toDrinkEntity() {
        return DrinkEntity.builder()
                .drink_id(drink_id)
                .name(name)
                .drink_type(drink_type)
                .description(description)
                .proof(proof)
                .price(price)
                .number_of_like(number_of_like)
                .build();
    }

    public static Drink from(DrinkEntity drinkEntity) {
        return Drink.builder()
                .drink_id(drinkEntity.getDrink_id())
                .name(drinkEntity.getName())
                .drink_type(drinkEntity.getDrink_type())
                .description(drinkEntity.getDescription())
                .proof(drinkEntity.getProof())
                .price(drinkEntity.getPrice())
                .number_of_like(drinkEntity.getNumber_of_like())
                .build();
    }
}
