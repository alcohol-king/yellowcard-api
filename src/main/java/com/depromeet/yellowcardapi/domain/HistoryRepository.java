package com.depromeet.yellowcardapi.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findByUserIdOrderByDrunkAtAsc(Long userId);
    List<History> findByDrunkAtBetweenOrderByDrunkAtAsc(LocalDate startDate, LocalDate endDate);

}
