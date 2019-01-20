package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.DrinkCapacity;

public class WineCapacityCalculator extends DrinkCapacityCalculator {

    private static final int CAPACITY_PER_LEVEL = 1;

    @Override
    public DrinkCapacity calculate(double level) {
        int integerPartOfLevel = (int) level;

        if (isGreaterThanMaxLevel(integerPartOfLevel)) {
            return DrinkCapacity.builder()
                    .isInfinity(true)
                    .build();
        }

        return DrinkCapacity.builder()
                .value(CAPACITY_PER_LEVEL * level)
                .build();
    }

    @Override
    public String getUnit() {
        return "잔";
    }

    private boolean isGreaterThanMaxLevel(int level) {
        return level >= 8;
    }
}
