package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.UserResponse;

public interface UserService {

    UserResponse findByUserId(Long userId);
}
