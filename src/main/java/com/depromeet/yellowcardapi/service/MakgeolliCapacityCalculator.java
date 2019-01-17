package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.DrinkCapacity;

public class MakgeolliCapacityCalculator extends DrinkCapacityCalculator {

    private static final int MAX_LEVEL = 7;

    @Override
    public DrinkCapacity calculate(double level) {
        double capacity = 0;

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
                capacity = level;
                break;
            default:
                throw new IllegalArgumentException("계산 불가능한 레벨입니다.");
        }

        if (level % 1 != 0) {
            capacity += 0.5;
        }

        return DrinkCapacity.builder()
                .value(capacity)
                .build();
    }

    @Override
    public String getUnit() {
        return "사발";
    }
}
