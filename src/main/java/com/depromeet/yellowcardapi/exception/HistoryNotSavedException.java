package com.depromeet.yellowcardapi.exception;

public class HistoryNotSavedException extends RuntimeException {

    public HistoryNotSavedException() { super("음주 이력을 저장하는 중에 오류가 발생했습니다."); }

    public HistoryNotSavedException(String message) { super(message); }

}
