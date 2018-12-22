package com.depromeet.yellowcardapi.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    Optional<Drink> findById(Integer drink_id);
    Page<Drink> findAll(Pageable pageable);
}
