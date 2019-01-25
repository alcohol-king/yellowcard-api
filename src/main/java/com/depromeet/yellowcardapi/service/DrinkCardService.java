package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.CreateDrinkCardRequest;

public interface DrinkCardService {

    void createDrinkCard(Long userId, CreateDrinkCardRequest request);
    void removeDrinkCard(Long userId, Long drinkCardId);
}
