package com.depromeet.yellowcardapi.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class KakaoAuthenticationFailedException extends RuntimeException {

    public KakaoAuthenticationFailedException() {
        super("카카오 인증에 실패했습니다.");
    }

    public KakaoAuthenticationFailedException(String message) {
        super(message);
    }
}
