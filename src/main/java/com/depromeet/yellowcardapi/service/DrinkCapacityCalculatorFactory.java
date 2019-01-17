package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.DrinkType;

public class DrinkCapacityCalculatorFactory {

    private DrinkCapacityCalculatorFactory() {}

    public static DrinkCapacityCalculator create(DrinkType drinkType) {
        switch (drinkType) {
            case BEER:
                return new BeerCapacityCalculator();
            case SOJU:
                return new SojuCapacityCalculator();
            case WINE:
                return new WineCapacityCalculator();
            case MAKGEOLLI:
                return new MakgeolliCapacityCalculator();
        }

        throw new IllegalArgumentException("계산 불가능한 주종입니다.");
    }
}
