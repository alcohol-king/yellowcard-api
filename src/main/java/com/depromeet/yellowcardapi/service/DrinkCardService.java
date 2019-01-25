package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.DrinkCard;

public interface DrinkCardService {

    void initDrinkCards(Long userId);
    DrinkCard createDrinkCard(Long userId, DrinkCard drinkCard);
    void removeDrinkCard(Long userId, Long drinkCardId);
}
