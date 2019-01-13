package com.depromeet.yellowcardapi.exception;

public class HistoryNotFoundException extends RuntimeException {

    public HistoryNotFoundException() { super("일치하는 음주 이력 정보가 존재하지 않습니다."); }

    public HistoryNotFoundException(String message) { super(message); }

}
