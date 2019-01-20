package com.depromeet.yellowcardapi.utils;

public abstract class DrinkCapacityPrinter {

    protected abstract String getUnit();

    public String print(double drinkCapacity) {
        return Math.round(drinkCapacity * 10) / 10 + getUnit();
    }
}
