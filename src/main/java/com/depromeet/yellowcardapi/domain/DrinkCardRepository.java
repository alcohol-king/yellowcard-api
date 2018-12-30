package com.depromeet.yellowcardapi.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkCardRepository extends JpaRepository<DrinkCard, Long> {

    List<DrinkCard> findByUserId(Long userId);
}
