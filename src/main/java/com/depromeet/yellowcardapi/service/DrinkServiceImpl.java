package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.User;
import com.depromeet.yellowcardapi.domain.UserRepository;
import com.depromeet.yellowcardapi.exception.DrinkNotFoundException;
import com.depromeet.yellowcardapi.domain.Drink;
import com.depromeet.yellowcardapi.domain.DrinkRepository;
import com.depromeet.yellowcardapi.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Drink createDrink(Drink drink) {
        drink.setNumberOfLike(0);

        return drinkRepository.save(drink);
    }

    @Override
    public List<Drink> listDrink() {
        return drinkRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public Drink getDrink(Long drinkId) {
        return drinkRepository.findById(drinkId)
                .orElseThrow(DrinkNotFoundException::new);
    }

    @Override
    public Drink likeDrink(Long userId, Long drinkId) {
        Drink drink = drinkRepository.findById(drinkId)
                .orElseThrow(DrinkNotFoundException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user.likeDrink(drink)) {
            drink.like();
        }

        userRepository.save(user);
        return drinkRepository.save(drink);
    }

}
