package com.depromeet.yellowcardapi.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "drink_history")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_history_id")
    private Long id;

    @Column(name = "user_id", updatable= false)
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
