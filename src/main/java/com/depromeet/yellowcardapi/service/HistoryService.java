package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.History;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public interface HistoryService {

    History createHistory(Long userId, History history);
    History getHistory(Long historyId);

    List<History> listHistory();
    List<History> listHistoryByUserId(Long userId);
    List<History>listHistoryByDate(LocalDate startDate, LocalDate endDate);

}
