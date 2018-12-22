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
    private Integer drink_id;
    @Column
    private String name;
    @Column
    private String drink_type;
    @Column
    private String description;
    @Column
    private Integer proof;
    @Column
    private Integer price;
    @Column
    private Integer number_of_like;
}
