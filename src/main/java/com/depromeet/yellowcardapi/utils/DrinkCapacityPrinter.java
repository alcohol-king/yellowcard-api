package com.depromeet.yellowcardapi.utils;

import com.depromeet.yellowcardapi.dto.DrinkCapacity;

public class DrinkCapacityPrinter {

    private String unit;

    public DrinkCapacityPrinter(String unit) {
        this.unit = unit;
    }

    public String print(DrinkCapacity capacity) {
        if (capacity.isInfinity()) {
            return "무한" + unit;
        } else {
            return Math.round(capacity.getValue() * 10) / 10 + unit;
        }
    }
}
