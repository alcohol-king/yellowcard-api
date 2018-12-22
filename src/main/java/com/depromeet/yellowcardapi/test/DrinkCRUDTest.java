package com.depromeet.yellowcardapi.test;

import com.depromeet.yellowcardapi.YellowcardApiApplication;
import com.depromeet.yellowcardapi.domain.Drink;
import com.depromeet.yellowcardapi.domain.DrinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = YellowcardApiApplication.class)
public class DrinkCRUDTest {
    @Autowired private DrinkRepository drinkRepository;

    private Drink drink;

    @Before
    public void setup() throws Exception {
        drink = new Drink();
        drink.builder()
                .name("밤막걸리")
                .drink_type("막걸리")
                .description("내가 좋아하는 막걸리")
                .price(11)
                .price(1000)
                .build();

        drinkRepository.save(drink);
        drinkRepository.flush();
    }

    @Test
    public void selectTest() throws Exception {
        Optional<Drink> selectDrink = drinkRepository.findById(drink.getDrink_id());
        assertEquals(selectDrink.get().getDrink_id(), drink.getDrink_id());
        assertEquals(selectDrink.get().getName(), drink.getName());
        assertNotNull(selectDrink.get().getName());
    }
}
