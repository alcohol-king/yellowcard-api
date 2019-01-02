package com.depromeet.yellowcardapi.dto;

import com.depromeet.yellowcardapi.domain.DrinkType;
import com.depromeet.yellowcardapi.domain.History;
import com.depromeet.yellowcardapi.utils.HelperFunctions;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.HashMap;

@Builder
@Getter
public class HistoryResponse {

    @JsonProperty(value = "history_id")
    private Long historyId;

    @JsonProperty(value = "user_id")
    private Long userId;

    private Integer beer;
    private Integer soju;
    private Integer wine;
    private Integer makgeolli;
    private HashMap<String, String> most;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty(value = "drunk_at")
    private LocalDate drunkAt;

    public static HistoryResponse from(History history) {
        return HistoryResponse.builder()
                .historyId(history.getId())
                .userId(history.getUserId())
                .beer(history.getBeer())
                .soju(history.getSoju())
                .wine(history.getWine())
                .makgeolli(history.getMakgeolli())
                .drunkAt(history.getDrunkAt())
                .build();
    }

    public static HistoryResponse fromContainsMost(History history) {
        return HistoryResponse.builder()
                .historyId(history.getId())
                .userId(history.getUserId())
                .most(history.getMost())
                .beer(history.getBeer())
                .soju(history.getSoju())
                .wine(history.getWine())
                .makgeolli(history.getMakgeolli())
                .drunkAt(history.getDrunkAt())
                .build();
    }

}
