package com.depromeet.yellowcardapi.dto;


import com.depromeet.yellowcardapi.domain.History;
import com.depromeet.yellowcardapi.utils.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class HistoryRequest {

    @JsonProperty(value = "history_id")
    private Long historyId;

    @JsonProperty(value = "user_id")
    private Long userId;

    private Integer beer;
    private Integer soju;
    private Integer wine;
    private Integer makgeolli;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty(value = "drunk_at")
    private LocalDate drunkAt;

    public History toHistory() {
        return History.builder()
                .id(historyId)
                .userId(userId)
                .beer(beer)
                .soju(soju)
                .wine(wine)
                .makgeolli(makgeolli)
                .drunkAt(drunkAt)
                .build();
    }

}
