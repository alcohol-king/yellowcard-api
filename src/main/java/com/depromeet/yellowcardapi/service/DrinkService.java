package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.Drink;

import java.util.List;

public interface DrinkService {

    Drink createDrink(Drink drink);
    List<Drink> listDrink();
    Drink getDrink(Long drinkId);
    Drink likeDrink(Long userId, Long drinkId);

    Drink updateDrink(Long drinkId, Drink drink);
    Boolean deleteDrink(Long drinkId);
}
