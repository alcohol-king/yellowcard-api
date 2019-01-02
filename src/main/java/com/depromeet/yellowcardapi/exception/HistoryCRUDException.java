package com.depromeet.yellowcardapi.exception;

public class HistoryCRUDException extends RuntimeException {

    public HistoryCRUDException() { super("음주 이력을 처리하는 중에 오류가 발생했습니다."); }

    public HistoryCRUDException(String message) { super(message); }

}
