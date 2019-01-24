package com.depromeet.yellowcardapi;

import com.depromeet.yellowcardapi.domain.DrinkCard;
import com.depromeet.yellowcardapi.domain.DrinkCardRepository;
import com.depromeet.yellowcardapi.domain.DrinkType;
import com.depromeet.yellowcardapi.domain.User;
import com.depromeet.yellowcardapi.exception.UnauthorizationException;
import com.depromeet.yellowcardapi.service.DrinkCardService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrinkCardServiceTests {

    private static final Long USER_ID = 1L;
    private static final Long DRINK_CARD_ID = 1L;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private DrinkCardService drinkCardService;

    @MockBean
    private DrinkCardRepository drinkCardRepository;

    @Before
    public void init() {
        User user = User.builder()
                .id(USER_ID)
                .name("주정뱅이")
                .statusMessage("그녀와의 맥주 한 잔.")
                .build();

        DrinkCard drinkCard = DrinkCard.builder()
                .drinkType(DrinkType.BEER)
                .drinkCapacity(1000d)
                .message("가뿐하게")
                .user(user)
                .build();

        given(drinkCardRepository.findById(DRINK_CARD_ID))
                .willReturn(Optional.of(drinkCard));
    }

    @Test
    public void removeDrinkCard() {
        drinkCardService.removeDrinkCard(USER_ID, DRINK_CARD_ID);
    }

    @Test
    public void removeDrinkCardWithAuthorizationException() {
        exception.expect(UnauthorizationException.class);
        drinkCardService.removeDrinkCard(2L, DRINK_CARD_ID);
    }
}
