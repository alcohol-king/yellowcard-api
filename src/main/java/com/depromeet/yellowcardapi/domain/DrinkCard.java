package com.depromeet.yellowcardapi.domain;

import com.depromeet.yellowcardapi.service.DrinkLevelCalculatorFactory;
import com.depromeet.yellowcardapi.utils.DrinkCapacityPrinter;
import com.depromeet.yellowcardapi.utils.DrinkCapacityPrinterFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    private double drinkCapacity;
    private boolean cardEnabled;
    private boolean labelEnabled;

    @PrePersist
    private void prePersist() {
        if (drinkCapacity == 0) drinkCapacity = 1;
    }

    @Builder
    public DrinkCard(User user, DrinkType drinkType, String message, double drinkCapacity) {
        this.user = user;
        this.drinkType = drinkType;
        this.message = message;
        this.drinkCapacity = drinkCapacity;
    }

    public double getLevel() {
        return DrinkLevelCalculatorFactory.create(drinkType)
                .calculateLevel(drinkCapacity);
    }

    public String getDrinkCapacityForView() {
        return DrinkCapacityPrinterFactory.create(drinkType)
                .print(drinkCapacity);
    }
}
