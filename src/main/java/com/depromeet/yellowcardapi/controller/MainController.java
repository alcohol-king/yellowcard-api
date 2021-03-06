package com.depromeet.yellowcardapi.controller;

import com.depromeet.yellowcardapi.config.annotation.UserId;
import com.depromeet.yellowcardapi.dto.MainResponse;
import com.depromeet.yellowcardapi.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/main")
    public MainResponse main(@ApiIgnore @UserId Long userId) {
        return mainService.findByUserId(userId);
    }
}
