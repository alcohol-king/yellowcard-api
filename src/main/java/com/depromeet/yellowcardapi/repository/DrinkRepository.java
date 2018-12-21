package com.depromeet.yellowcardapi.repository;

import com.depromeet.yellowcardapi.entity.DrinkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrinkRepository extends JpaRepository<DrinkEntity, Integer> {
    Optional<DrinkEntity> findById(Integer drink_id);
    Page<DrinkEntity> findAll(Pageable pageable);
}
