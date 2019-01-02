package com.depromeet.yellowcardapi.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class DrinkHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int beer;
    private int soju;
    private int wine;
    private int makgeolli;

    @Column(nullable = false)
    private LocalDate drunkAt;
}
