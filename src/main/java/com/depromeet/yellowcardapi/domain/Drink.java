package com.depromeet.yellowcardapi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Drink")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drink {
    @Id
    @Column(name = "drink_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer drinkId;
    @Column
    private String name;
    @Column
    private String drinkType;
    @Column
    private String description;
    @Column
    private Integer proof;
    @Column
    private Integer price;
    @Column
    private Integer numberOfLike;
}
