package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.DrinkCard;
import com.depromeet.yellowcardapi.domain.DrinkCardRepository;
import com.depromeet.yellowcardapi.dto.CreateDrinkCardRequest;
import com.depromeet.yellowcardapi.exception.DrinkCardNotFoundException;
import com.depromeet.yellowcardapi.exception.DrinkNotFoundException;
import com.depromeet.yellowcardapi.exception.UnauthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkCardServiceImpl implements DrinkCardService {

    private final DrinkCardRepository drinkCardRepository;

    @Override
    public DrinkCard createDrinkCard(Long userId, final DrinkCard drinkCardToUpdate) {
        List<DrinkCard> drinkCards = drinkCardRepository.findByUserId(userId);

        DrinkCard drinkCard = drinkCards.stream()
                .filter(d -> d.getDrinkType() == drinkCardToUpdate.getDrinkType())
                .findFirst()
                .orElseThrow(DrinkCardNotFoundException::new);

        drinkCard.setMessage(drinkCardToUpdate.getMessage());
        drinkCard.setCardEnabled(true);
        return drinkCardRepository.save(drinkCard);
    }

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
