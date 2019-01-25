package com.depromeet.yellowcardapi.exception;

public class DrinkCardNotFoundException extends RuntimeException {

    public DrinkCardNotFoundException() {
        super("일치하는 주량 카드가 존재하지 않습니다.");
    }

    public DrinkCardNotFoundException(String message) {
        super(message);
    }
}
