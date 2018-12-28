package com.depromeet.yellowcardapi.controller;

import com.depromeet.yellowcardapi.config.annotation.UserId;
import com.depromeet.yellowcardapi.domain.History;
import com.depromeet.yellowcardapi.dto.HistoryRequest;
import com.depromeet.yellowcardapi.dto.HistoryResponse;
import com.depromeet.yellowcardapi.service.HistoryService;
import com.depromeet.yellowcardapi.utils.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    private final Environment environment;

    @PostMapping("/histories")
    @ResponseStatus(HttpStatus.CREATED)
    public HistoryResponse createHistory(@UserId Long userId, @RequestBody HistoryRequest historyRequest) {
        History history = historyService.createHistory(userId, historyRequest.toHistory());
        return HistoryResponse.from(history);
    }

    @GetMapping("/histories/{historyId}")
    @ResponseStatus(HttpStatus.OK)
    public HistoryResponse getHistory(@PathVariable Long historyId) {
        History history = historyService.getHistory(historyId);
        return HistoryResponse.from(history);
    }

    @GetMapping("/histories")
    @ResponseStatus(HttpStatus.OK)
    public List<HistoryResponse> listHistory() {
        List<String> envs = Arrays.asList(environment.getActiveProfiles());

        return historyService.listHistory().stream()
                .map(HistoryResponse::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/histories/me")
    @ResponseStatus(HttpStatus.OK)
    public List<HistoryResponse> listHistoryByUserId(@UserId Long userId) {
        return historyService.listHistoryByUserId(userId).stream()
                .map(HistoryResponse::from)
                .collect(Collectors.toList());
    }

    @PostMapping("/histories/me")
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start_date", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "end_date", required = true, dataType = "string", paramType = "query")
    })
    public List<HistoryResponse> listHistoryByDate(@RequestBody Map<Object, Object> params) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse((String) params.get("start_date"), formatter);
        LocalDate endDate   = LocalDate.parse((String) params.get("end_date"),   formatter);

        return historyService.listHistoryByDate(startDate, endDate).stream()
                .map(HistoryResponse::from)
                .collect(Collectors.toList());
    }

}
