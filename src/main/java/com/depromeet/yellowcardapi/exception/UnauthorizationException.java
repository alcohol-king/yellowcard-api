package com.depromeet.yellowcardapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizationException extends RuntimeException {

    public UnauthorizationException() {
        super("권한이 없습니다.");
    }

    public UnauthorizationException(String message) {
        super(message);
    }
}
