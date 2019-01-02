package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.History;
import com.depromeet.yellowcardapi.domain.HistoryRepository;
import com.depromeet.yellowcardapi.domain.User;
import com.depromeet.yellowcardapi.domain.UserRepository;
import com.depromeet.yellowcardapi.exception.HistoryNotFoundException;
import com.depromeet.yellowcardapi.exception.HistoryCRUDException;
import com.depromeet.yellowcardapi.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    @Override
    public History createHistory(Long userId, History history) throws HistoryCRUDException {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        history.setUserId(userId);
        Long result = user.addHistory(history);

        return result == -1 ? historyRepository.save(history) : updateHistory(result, history);
    }

    @Override
    public History getHistory(Long historyId) {
        return historyRepository.findById(historyId)
                .orElseThrow(HistoryNotFoundException::new);
    }

    @Override
    public History updateHistory(Long historyId, History history) {
        historyRepository.findById(historyId)
                .orElseThrow(HistoryNotFoundException::new);

        history.setId(historyId);
        return historyRepository.save(history);
    }

    @Override
    public Boolean deleteHistory(Long historyId) {
        History history = historyRepository.findById(historyId)
                .orElseThrow(HistoryNotFoundException::new);

        historyRepository.delete(history);
        return true;
    }

    @Override
    public List<History> listHistory() {
        return historyRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<History> listHistoryByUserId(Long userId) {
        return historyRepository.findByUserIdOrderByDrunkAtAsc(userId).stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<History> listHistoryByDate(LocalDate startDate, LocalDate endDate) {
        return historyRepository.findByDrunkAtBetweenOrderByDrunkAtAsc(startDate, endDate).stream()
                .collect(Collectors.toList());
    }

}
