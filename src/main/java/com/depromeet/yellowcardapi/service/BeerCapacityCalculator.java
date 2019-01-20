package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.DrinkCapacity;

public class BeerCapacityCalculator extends DrinkCapacityCalculator {

    private static final int[] CAPACITY_BY_LEVEL = {
            300, 500, 1000, 1500, 2000, 3000, 5000
    };

    @Override
    public DrinkCapacity calculate(double level) {
        int integerPartOfLevel = (int) level;

        if (isGreaterThanMaxLevel(integerPartOfLevel)) {
            return DrinkCapacity.builder()
                    .isInfinity(true)
                    .build();
        }

        int capacity = CAPACITY_BY_LEVEL[integerPartOfLevel - 1];

        int nextLevel = integerPartOfLevel + 1;
        if (!isGreaterThanMaxLevel(nextLevel)) {
            int nextCapacity = CAPACITY_BY_LEVEL[nextLevel - 1];
            int differenceOfLevel = nextCapacity - capacity;

            capacity += differenceOfLevel * (level - integerPartOfLevel);
        }

        return DrinkCapacity.builder()
                .value(capacity)
                .build();
    }

    @Override
    public String getUnit() {
        return "cc";
    }

    private boolean isGreaterThanMaxLevel(int level) {
        return level >= 8;
    }
}
