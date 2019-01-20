package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.DrinkType;

public class DrinkLevelCalculatorFactory {

    private DrinkLevelCalculatorFactory() {}

    public static DrinkLevelCalculator create(DrinkType drinkType) {
        switch (drinkType) {
            case BEER:
                return new BeerLevelCalculator();
            case SOJU:
                return new SojuLevelCalculator();
            case WINE:
                return new WineLevelCalculator();
            case MAKGEOLLI:
                return new MakgeolliLevelCalculator();
        }

        throw new IllegalArgumentException("계산 불가능한 주종입니다.");
    }
}
