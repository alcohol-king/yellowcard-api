package com.depromeet.yellowcardapi.controller;

import com.depromeet.yellowcardapi.config.annotation.UserId;
import com.depromeet.yellowcardapi.dto.CreateDrinkCardRequest;
import com.depromeet.yellowcardapi.service.DrinkCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DrinkCardController {

    private final DrinkCardService drinkCardService;

    @PostMapping("/cards")
    public void createDrinkCard(@UserId Long userId, CreateDrinkCardRequest request) {
        drinkCardService.createDrinkCard(userId, request);
    }

    @DeleteMapping("/cards/{drinkCardId}")
    public void removeDrinkCard(@UserId Long userId, @PathVariable Long drinkCardId) {
        drinkCardService.removeDrinkCard(userId, drinkCardId);
    }
}
