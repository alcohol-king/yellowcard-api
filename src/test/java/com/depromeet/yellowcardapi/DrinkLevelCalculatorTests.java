package com.depromeet.yellowcardapi;

import com.depromeet.yellowcardapi.domain.DrinkType;
import com.depromeet.yellowcardapi.service.DrinkLevelCalculator;
import com.depromeet.yellowcardapi.service.DrinkLevelCalculatorFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrinkLevelCalculatorTests {

    private DrinkLevelCalculator beerLevelCalculator;
    private DrinkLevelCalculator sojuLevelCalculator;

    @Before
    public void init() {
        beerLevelCalculator = DrinkLevelCalculatorFactory.create(DrinkType.BEER);
        sojuLevelCalculator = DrinkLevelCalculatorFactory.create(DrinkType.SOJU);
    }

    @Test
    public void calculateBeerLevel() {
        double level = beerLevelCalculator.calculateLevel(0.0);
        assertEquals(0.0, level, 0);

        level = beerLevelCalculator.calculateLevel(200);
        assertEquals(0.7, level, 0);

        level = beerLevelCalculator.calculateLevel(300);
        assertEquals(1.0, level, 0);

        level = beerLevelCalculator.calculateLevel(4800);
        assertEquals(6.9, level, 0);

        level = beerLevelCalculator.calculateLevel(5000);
        assertEquals(7.0, level, 0);

        level = beerLevelCalculator.calculateLevel(5200);
        assertEquals(7.0, level, 0);
    }

    @Test
    public void calculateEtcLevel() {
        double level = sojuLevelCalculator.calculateLevel(0.0);
        assertEquals(0.0, level, 0);

        level = sojuLevelCalculator.calculateLevel(0.1);
        assertEquals(0.1, level, 0);

        level = sojuLevelCalculator.calculateLevel(1.0);
        assertEquals(1.0, level, 0);

        level = sojuLevelCalculator.calculateLevel(1.1);
        assertEquals(1.1, level, 0);

        level = sojuLevelCalculator.calculateLevel(6.9);
        assertEquals(6.9, level, 0);

        level = sojuLevelCalculator.calculateLevel(7.1);
        assertEquals(7.0, level, 0);

        level = sojuLevelCalculator.calculateLevel(4.54);
        assertEquals(4.5, level, 0);

        level = sojuLevelCalculator.calculateLevel(4.55);
        assertEquals(4.6, level, 0);
    }
}
