package com.depromeet.yellowcardapi.service;

public class BeerLevelCalculator extends DrinkLevelCalculator {

    private static final double[] DRINK_CAPACITY_BY_LEVEL = {
            0, 300, 500, 1000, 1500, 2000, 3000, 5000
    };

    @Override
    public double drinkCapacityToLevel(double drinkCapacity) {
        double level = 0;

        if (drinkCapacity >= DRINK_CAPACITY_BY_LEVEL[DRINK_CAPACITY_BY_LEVEL.length - 1]) {
            return getMaxLevel();
        }

        for (int i = 0; i < DRINK_CAPACITY_BY_LEVEL.length - 1; i++) {
            double currentDrinkCapacity = DRINK_CAPACITY_BY_LEVEL[i];
            double nextDrinkCapacity = DRINK_CAPACITY_BY_LEVEL[i + 1];

            if (drinkCapacity >= currentDrinkCapacity && drinkCapacity < nextDrinkCapacity) {
                level = i;

                double remainedDrinkCapacity = drinkCapacity - currentDrinkCapacity;
                double differenceOfDrinkCapacity = nextDrinkCapacity - currentDrinkCapacity;

                double decimalPartOfLevel = remainedDrinkCapacity / differenceOfDrinkCapacity;
                level += decimalPartOfLevel;

                break;
            }
        }

        return level;
    }

    @Override
    public double getMaxLevel() {
        return 7;
    }
}
