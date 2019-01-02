package com.depromeet.yellowcardapi.exception;

public class DrinkNotFoundException extends RuntimeException {

    public DrinkNotFoundException() { super("술과사전에 일치하는 주종 정보가 존재하지 않습니다."); }

    public DrinkNotFoundException(String message) {
        super(message);
    }

}