package com.depromeet.yellowcardapi.test;

import com.depromeet.yellowcardapi.YellowcardApiApplication;
import com.depromeet.yellowcardapi.domain.Drink;
import com.depromeet.yellowcardapi.domain.DrinkRepository;
import com.depromeet.yellowcardapi.service.DrinkService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = YellowcardApiApplication.class)
public class DrinkCRUDTest {
    @Autowired private DrinkRepository drinkRepository;
    @Autowired private DrinkService drinkService;

    private Drink drink;

    @Before
    public void setup() throws Exception {
        drink = new Drink();
        drink.setName("밤막걸리");
        drink.setDrinkType("막걸리");
        drink.setDescription("내가 좋아하는 막걸리");
        drink.setProof(11);
        drink.setPrice(1000);

        drinkService.createDrink(drink);
    }

    @Test
    public void selectTest() throws Exception {
        Drink selectedDrink = drinkRepository.findById(drink.getDrinkId()).get();
        assertEquals(selectedDrink.getDrinkId(), drink.getDrinkId());
        assertEquals(selectedDrink.getName(), drink.getName());
        assertNotNull(selectedDrink.getName());
    }

    @Test
    public void likeTest() throws Exception {
        Drink selectedDrink = drinkRepository.findById(drink.getDrinkId()).get();
        int beforeNumberOfLike = selectedDrink.getNumberOfLike();

        drinkService.increaseLike(selectedDrink.getDrinkId());

        selectedDrink = drinkRepository.findById(drink.getDrinkId()).get();
        int afterNumberOfLike = selectedDrink.getNumberOfLike();

        assertThat(afterNumberOfLike, is(beforeNumberOfLike+1));
    }
}
