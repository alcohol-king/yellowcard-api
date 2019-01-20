package com.depromeet.yellowcardapi.exception;

public class DrinkCRUDException extends RuntimeException {

    public DrinkCRUDException() { super("술과사전을 처리하는 중에 오류가 발생했습니다."); }

    public DrinkCRUDException(String message) { super(message); }

}
