package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.domain.*;
import com.depromeet.yellowcardapi.exception.HistoryCRUDException;
import com.depromeet.yellowcardapi.exception.HistoryNotFoundException;
import com.depromeet.yellowcardapi.exception.UnknownDrinkTypeException;
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
    private final DrinkCardRepository drinkCardRepository;

    @Override
    public History createHistory(Long userId, History historyToCreate) throws HistoryCRUDException {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        historyToCreate.setUserId(userId);
        Long result = user.addHistory(historyToCreate);

        History history = result == -1 ? historyRepository.save(historyToCreate) : updateHistory(result, historyToCreate);

        updateDrinkCapacityOfDrinkCards(userId, history);

        return history;
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

    private List<DrinkCard> updateDrinkCapacityOfDrinkCards(Long userId, History history) {
        List<DrinkCard> drinkCards = drinkCardRepository.findByUserId(userId);
        return drinkCards.stream()
                .map(drinkCard -> {
                    double averageDrinkCapacity = 0;

                    switch (drinkCard.getDrinkType()) {
                        case BEER:
                            averageDrinkCapacity = (drinkCard.getDrinkCapacity() + history.getBeer()) / 2d;
                            break;
                        case SOJU:
                            averageDrinkCapacity = (drinkCard.getDrinkCapacity() + history.getSoju()) / 2d;
                            break;
                        case WINE:
                            averageDrinkCapacity = (drinkCard.getDrinkCapacity() + history.getWine()) / 2d;
                            break;
                        case MAKGEOLLI:
                            averageDrinkCapacity = (drinkCard.getDrinkCapacity() + history.getMakgeolli()) / 2d;
                            break;
                        default:
                            throw new UnknownDrinkTypeException();
                    }

                    drinkCard.setDrinkCapacity(averageDrinkCapacity);
                    return drinkCardRepository.save(drinkCard);
                })
                .collect(Collectors.toList());
    }
}
