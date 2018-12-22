package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.exception.NoContentException;
import com.depromeet.yellowcardapi.domain.Drink;
import com.depromeet.yellowcardapi.domain.DrinkRepository;
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
        return drinkRepository.save(drink);
    }

    @Override
    public List<Drink> listDrink() {
        return drinkRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public Drink getDrink(Integer drink_id) {
        return drinkRepository.findById(drink_id)
                .orElseThrow(() -> new NoContentException("Drink not found."));
    }
}