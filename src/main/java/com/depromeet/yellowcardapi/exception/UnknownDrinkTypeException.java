package com.depromeet.yellowcardapi.exception;

public class UnknownDrinkTypeException extends RuntimeException {

    public UnknownDrinkTypeException() {
        super("알 수 없는 주종입니다.");
    }

    public UnknownDrinkTypeException(String message) {
        super(message);
    }
}
