package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.DrinkCapacity;

import java.text.DecimalFormat;

public class DrinkCapacityPrinter {

    private String unit;

    public DrinkCapacityPrinter(String unit) {
        this.unit = unit;
    }

    public String print(DrinkCapacity capacity) {
        if (capacity.isInfinity()) {
            return "무한" + unit;
        } else {
            return new DecimalFormat("#.##").format(capacity.getValue()) + unit;
        }
    }
}
