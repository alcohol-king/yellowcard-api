package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.DrinkCapacity;

public class BeerCapacityCalculator extends DrinkCapacityCalculator {

    private static final int MAX_LEVEL = 8;

    @Override
    public DrinkCapacity calculate(double level) {
        int capacity = 0;

        if (level == MAX_LEVEL) {
            return DrinkCapacity.builder()
                    .isInfinity(true)
                    .build();
        }

        switch ((int) level) {
            case 1:
                capacity = 300;
                break;
            case 2:
                capacity = 500;
                break;
            case 3:
                capacity = 1000;
                break;
            case 4:
                capacity = 1500;
                break;
            case 5:
                capacity = 2000;
                break;
            case 6:
                capacity = 3000;
                break;
            case 7:
                capacity = 5000;
                break;
            default:
                throw new IllegalArgumentException("계산 불가능한 레벨입니다.");
        }

        return DrinkCapacity.builder()
                .value(capacity)
                .build();
    }

    @Override
    public String getUnit() {
        return "cc";
    }
}
