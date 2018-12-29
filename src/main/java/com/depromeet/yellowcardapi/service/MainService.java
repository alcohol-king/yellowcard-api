package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.dto.MainResponse;

public interface MainService {

    MainResponse findByUserId(Long userId);
}
