package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.DrinkCard;
import com.depromeet.yellowcardapi.domain.DrinkCardRepository;
import com.depromeet.yellowcardapi.exception.DrinkNotFoundException;
import com.depromeet.yellowcardapi.exception.UnauthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrinkCardServiceImpl implements DrinkCardService {

    private final DrinkCardRepository drinkCardRepository;

    @Override
    public void removeDrinkCard(Long userId, Long drinkCardId) {
        DrinkCard drinkCard = drinkCardRepository.findById(drinkCardId)
                .orElseThrow(DrinkNotFoundException::new);

        if (!userId.equals(drinkCard.getUser().getId())) {
            throw new UnauthorizationException();
        }

        drinkCard.setCardEnabled(false);
        drinkCardRepository.save(drinkCard);
    }
}
