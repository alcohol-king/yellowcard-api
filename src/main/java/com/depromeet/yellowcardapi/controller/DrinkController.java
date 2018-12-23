package com.depromeet.yellowcardapi.controller;

import com.depromeet.yellowcardapi.dto.DrinkRequest;
import com.depromeet.yellowcardapi.dto.DrinkResponse;
import com.depromeet.yellowcardapi.domain.Drink;
import com.depromeet.yellowcardapi.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/drinks")
    @ResponseStatus(HttpStatus.OK)
    public List<DrinkResponse> listDrink() {
        return drinkService.listDrink().stream()
                .map(DrinkResponse::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/drinks/{drink_id}")
    @ResponseStatus(HttpStatus.OK)
    public DrinkResponse getDrink(@PathVariable Integer drink_id) {
        Drink drink = drinkService.getDrink(drink_id);
        return DrinkResponse.from(drink);
    }

    @GetMapping("/drinks/likes/{drink_id}")
    @ResponseStatus(HttpStatus.OK)
    public DrinkResponse likeDrink(@PathVariable Integer drink_id) {
        Drink drink = drinkService.addLike(drink_id);
        return DrinkResponse.from(drink);
    }
}
