package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.entity.DrinkEntity;
import com.depromeet.yellowcardapi.exception.NoContentException;
import com.depromeet.yellowcardapi.model.Drink;
import com.depromeet.yellowcardapi.repository.DrinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkServiceImpl implements DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Transactional
    @Override
    public Drink createDrink(Drink drink) {
        DrinkEntity drinkEntity = drink.toDrinkEntity();
        return Drink.from(drinkRepository.save(drinkEntity));
    }

    @Override
    public List<Drink> listDrink() {
        return drinkRepository.findAll().stream()
                .map(Drink::from)
                .collect(Collectors.toList());
    }

    @Override
    public Drink getDrink(Integer drink_id) {
        return drinkRepository.findById(drink_id)
                .map(Drink::from)
                .orElseThrow(() -> new NoContentException("Drink not found."));

    }
}
