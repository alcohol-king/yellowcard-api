package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.DrinkCard;

public interface DrinkCardService {

    DrinkCard createDrinkCard(Long userId, DrinkCard drinkCard);

    void removeDrinkCard(Long userId, Long drinkCardId);
}
