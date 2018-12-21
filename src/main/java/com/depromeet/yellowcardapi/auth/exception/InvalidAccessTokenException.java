package com.depromeet.yellowcardapi.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidAccessTokenException extends RuntimeException {

    public InvalidAccessTokenException() {
        super("유효하지 않은 토큰입니다.");
    }
}
