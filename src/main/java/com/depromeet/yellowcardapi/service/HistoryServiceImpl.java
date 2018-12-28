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
    public History createHistory(Long userId, History history) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        history.setUserId(userId);

        if (user.addHistory(history)) {
            return historyRepository.save(history);
        } else {
            throw new HistoryCRUDException("사용자 도메인에 음주 이력을 추가하던 중에 오류가 발생했습니다.");
        }
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

        System.out.println("하하: " + history.toString());

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
