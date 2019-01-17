package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.DrinkCapacity;

public class WineCapacityCalculator extends DrinkCapacityCalculator {

    private static final int MAX_LEVEL = 8;

    @Override
    public DrinkCapacity calculate(double level) {
        if (level == MAX_LEVEL) {
            return DrinkCapacity.builder()
                    .isInfinity(true)
                    .build();
        }

        switch ((int) level) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return DrinkCapacity.builder()
                        .value(level)
                        .build();
            default:
                throw new IllegalArgumentException("계산 불가능한 레벨입니다.");
        }
    }

    @Override
    public String getUnit() {
        return "잔";
    }
}
