package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.DrinkCard;
import com.depromeet.yellowcardapi.domain.DrinkCardRepository;
import com.depromeet.yellowcardapi.domain.User;
import com.depromeet.yellowcardapi.domain.UserRepository;
import com.depromeet.yellowcardapi.dto.DrinkLabelResponse;
import com.depromeet.yellowcardapi.dto.UserResponse;
import com.depromeet.yellowcardapi.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DrinkCardRepository drinkCardRepository;

    public UserResponse findByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<DrinkCard> drinkCards = drinkCardRepository.findByUserId(userId);

        return UserResponse.builder()
                .userName(user.getName())
                .statusMessage(user.getStatusMessage())
                .profileImageUrl(user.getThumbnailImageUrl())
                .labels(DrinkLabelResponse.from(drinkCards))
                .build();
    }
}
