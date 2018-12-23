package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.Drink;

import java.util.List;

public interface DrinkService {
    Drink createDrink(Drink drink);
    List<Drink> listDrink();
    Drink getDrink(Integer drinkId);
    Drink increaseLike(Integer drinkId);
}
