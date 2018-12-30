package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.DrinkCard;
import com.depromeet.yellowcardapi.domain.DrinkCardRepository;
import com.depromeet.yellowcardapi.domain.User;
import com.depromeet.yellowcardapi.domain.UserRepository;
import com.depromeet.yellowcardapi.dto.DrinkCardResponse;
import com.depromeet.yellowcardapi.dto.MainResponse;
import com.depromeet.yellowcardapi.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final UserRepository userRepository;
    private final DrinkCardRepository drinkCardRepository;

    @Override
    public MainResponse findByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<DrinkCard> drinkCards = drinkCardRepository.findByUserId(userId);

        return MainResponse.builder()
                .userName(user.getName())
                .statusMessage(user.getStatusMessage())
                .profileImageUrl(user.getThumbnailImageUrl())
                .drinkCards(DrinkCardResponse.from(drinkCards))
                .build();
    }
}
