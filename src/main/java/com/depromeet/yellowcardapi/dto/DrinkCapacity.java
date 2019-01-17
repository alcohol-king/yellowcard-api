package com.depromeet.yellowcardapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DrinkCapacity {

    private boolean isInfinity;
    private double value;
}
