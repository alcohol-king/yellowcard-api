package com.depromeet.yellowcardapi.controller;

import com.depromeet.yellowcardapi.dto.DrinkRequest;
import com.depromeet.yellowcardapi.dto.DrinkResponse;
import com.depromeet.yellowcardapi.model.Drink;
import com.depromeet.yellowcardapi.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * RequiredArgsConstructor: final이나 NonNull인 필드 값만 파라미터로 받습니다.
 */
@RestController
@RequiredArgsConstructor
public class DrinkController {
    private final DrinkService drinkService;

    @PostMapping("/drinks")
    @ResponseStatus(HttpStatus.CREATED)
    public DrinkResponse createDrink(@RequestBody DrinkRequest drinkRequest) {
        Drink drink = drinkService.createDrink(drinkRequest.toDrink());
        return DrinkResponse.from(drink);
    }

}
