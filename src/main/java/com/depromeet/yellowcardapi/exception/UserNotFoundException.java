package com.depromeet.yellowcardapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("일치하는 사용자 정보가 존재하지 않습니다.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
