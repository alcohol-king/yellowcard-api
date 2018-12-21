package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.model.Drink;

import java.util.List;

public interface DrinkService {
    Drink createDrink(Drink drink);
    List<Drink> listDrink();
    Drink getDrink(Integer drink_id);
}
