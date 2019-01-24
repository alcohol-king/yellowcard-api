package com.depromeet.yellowcardapi.controller;

import com.depromeet.yellowcardapi.config.annotation.UserId;
import com.depromeet.yellowcardapi.dto.CreateDrinkCardRequest;
import com.depromeet.yellowcardapi.service.DrinkCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DrinkCardController {

    private final DrinkCardService drinkCardService;

    public void createDrinkCard(@UserId Long userId, CreateDrinkCardRequest request) {
        drinkCardService.createDrinkCard(userId, request);
    }
}
