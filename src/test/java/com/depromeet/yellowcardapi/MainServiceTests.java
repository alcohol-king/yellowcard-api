package com.depromeet.yellowcardapi;

import com.depromeet.yellowcardapi.domain.*;
import com.depromeet.yellowcardapi.dto.DrinkCardResponse;
import com.depromeet.yellowcardapi.dto.MainResponse;
import com.depromeet.yellowcardapi.service.MainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainServiceTests {

    @Autowired
    private MainService mainService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private DrinkCardRepository drinkCardRepository;

    private User user;
    private List<DrinkCard> drinkCards;

    @Before
    public void init() {
        user = new User(1L, "조성빈", null, null, "힘든 날에는 소주가 땡겨, 가끔은 쏘맥도");
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        drinkCards = new ArrayList<>();

        DrinkCard beerCard = DrinkCard.builder()
                .user(user)
                .drinkType(DrinkType.BEER)
                .message("3잔까지는 깔끔하게")
                .level(2)
                .build();

        DrinkCard sojuCard = DrinkCard.builder()
                .user(user)
                .drinkType(DrinkType.SOJU)
                .message("2병까지는 멀쩡하게")
                .level(4)
                .build();

        DrinkCard wineCard = DrinkCard.builder()
                .user(user)
                .drinkType(DrinkType.WINE)
                .message("2잔까지는 즐기면서")
                .level(2)
                .build();

        DrinkCard makgeolliCard = DrinkCard.builder()
                .user(user)
                .drinkType(DrinkType.MAKGEOLLI)
                .message("4잔까지는 깔끔하게")
                .level(3)
                .build();

        drinkCards.add(beerCard);
        drinkCards.add(sojuCard);
        drinkCards.add(wineCard);
        drinkCards.add(makgeolliCard);

        given(drinkCardRepository.findByUserId(user.getId())).willReturn(drinkCards);
    }

    @Test
    public void findByUserId() throws JsonProcessingException {
        MainResponse response = mainService.findByUserId(user.getId());

        assertEquals(response.getUserName(), user.getName());
        assertEquals(response.getStatusMessage(), user.getStatusMessage());
        assertEquals(response.getProfileImageUrl(), user.getThumbnailImageUrl());

        for (int i = 0; i < response.getDrinkCards().size(); i++) {
            DrinkCardResponse drinkCardResponse = response.getDrinkCards().get(i);
            DrinkCard drinkCard = drinkCards.get(i);

            assertEquals(drinkCardResponse.getDrinkType(), drinkCard.getDrinkType().name());
            assertEquals(drinkCardResponse.getMessage(), drinkCard.getMessage());
        }

        System.out.println(new ObjectMapper().writeValueAsString(response));
    }
}
