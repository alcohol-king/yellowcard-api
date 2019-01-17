package com.depromeet.yellowcardapi.domain;

import com.depromeet.yellowcardapi.service.DrinkCapacityCalculatorFactory;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class DrinkCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_card_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private DrinkType drinkType;

    @Column(length = 20, nullable = false)
    private String message;

    private int level;
    private boolean cardEnabled;
    private boolean labelEnabled;

    @PrePersist
    private void prePersist() {
        if (level == 0) level = 1;
    }

    @Builder
    public DrinkCard(User user, DrinkType drinkType, String message, int level) {
        this.user = user;
        this.drinkType = drinkType;
        this.message = message;
        this.level = level;
    }

    public String getDrinkCapacity() {
        return DrinkCapacityCalculatorFactory.create(drinkType)
                .calculateCapacity(level);
    }
}
