package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.utils.DrinkCapacityPrinter;

public abstract class DrinkLevelCalculator {

    protected abstract double drinkCapacityToLevel(double level);

    protected abstract double getMaxLevel();

    public double calculateLevel(double drinkCapacity) {
        double level = drinkCapacityToLevel(drinkCapacity);
        if (isGreaterThanOrEqualToMaxLevel(level)) {
            level = getMaxLevel();
        }

        return Math.round(level * 10) / 10d;
    }

    private boolean isGreaterThanOrEqualToMaxLevel(double level) {
        return level >= getMaxLevel();
    }
}
