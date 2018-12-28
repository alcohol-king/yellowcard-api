package com.depromeet.yellowcardapi.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "drink_history")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_history_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private Integer beer;

    @Column
    private Integer soju;

    @Column
    private Integer wine;

    @Column
    private Integer makgeolli;

    @Column
    private LocalDate drunkAt;

}
