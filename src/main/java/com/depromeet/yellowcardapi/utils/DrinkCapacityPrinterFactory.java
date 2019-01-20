package com.depromeet.yellowcardapi.utils;

import com.depromeet.yellowcardapi.domain.DrinkType;
import com.depromeet.yellowcardapi.service.BeerLevelCalculator;
import com.depromeet.yellowcardapi.service.MakgeolliLevelCalculator;
import com.depromeet.yellowcardapi.service.SojuLevelCalculator;
import com.depromeet.yellowcardapi.service.WineLevelCalculator;

public class DrinkCapacityPrinterFactory {

    public static DrinkCapacityPrinter create(DrinkType drinkType) {
        switch (drinkType) {
            case BEER:
                return new BeerCapacityPrinter();
            case SOJU:
                return new SojuCapacityPrinter();
            case WINE:
                return new WineCapacityPrinter();
            case MAKGEOLLI:
                return new MakgeolliCapacityPrinter();
        }

        throw new IllegalArgumentException("출력 불가능한 주종입니다.");
    }
}
