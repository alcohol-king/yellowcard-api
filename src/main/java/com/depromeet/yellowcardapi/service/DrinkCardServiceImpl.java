package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.DrinkCard;
import com.depromeet.yellowcardapi.domain.DrinkCardRepository;
import com.depromeet.yellowcardapi.dto.CreateDrinkCardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkCardServiceImpl implements DrinkCardService {

    private final DrinkCardRepository drinkCardRepository;

    @Override
    public void createDrinkCard(Long userId, CreateDrinkCardRequest request) {
        request.getDrinkType();

        List<DrinkCard> drinkCards = drinkCardRepository.findByUserId(userId);
        drinkCards.stream();
    }
}
