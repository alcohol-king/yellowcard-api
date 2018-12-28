package com.depromeet.yellowcardapi.dto;

import com.depromeet.yellowcardapi.domain.History;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

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

}
