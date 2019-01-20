package com.depromeet.yellowcardapi.service;

public class SojuLevelCalculator extends DrinkLevelCalculator {

    @Override
    public double drinkCapacityToLevel(double drinkCapacity) {
        return drinkCapacity;
    }

    @Override
    public double getMaxLevel() {
        return 7;
    }
}
