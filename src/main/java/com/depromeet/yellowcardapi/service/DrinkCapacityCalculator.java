package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.DrinkCapacity;
import com.depromeet.yellowcardapi.utils.DrinkCapacityPrinter;

public abstract class DrinkCapacityCalculator {

    protected abstract DrinkCapacity calculate(double level);

    protected abstract String getUnit();

    protected DrinkCapacityPrinter getPrinter() {
        return new DrinkCapacityPrinter(getUnit());
    }

    public String calculateCapacity(double level) {
        return getPrinter().print(calculate(level));
    }
}
