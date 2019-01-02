package com.depromeet.yellowcardapi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "drink")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id")
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(value = EnumType.STRING)
    private DrinkType drinkType;

    @Column
    private String description;

    @Column
    private Double proof;

    @Column
    private Integer price;

    @Column
    private int numberOfLike;

    public void like() {
        numberOfLike++;
    }

    public static class DrinkBuilder {
        public DrinkBuilder drinkType(DrinkType drinkType) {
            this.drinkType = drinkType;
            return this;
        }

        public DrinkBuilder drinkType(String drinkType) {
            this.drinkType = DrinkType.valueOf(drinkType);
            return this;
        }
    }
}
