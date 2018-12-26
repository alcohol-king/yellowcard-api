package com.depromeet.yellowcardapi.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DrinkType {

    BEER(1, "맥주"),
    SOJU(2, "소주"),
    WINE(3, "와인"),
    MAKGEOLLI(4, "막걸리");

    private int code;
    private String value;
}
