package com.depromeet.yellowcardapi.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "external_id")
    private Long externalId;

    @Column(length = 20)
    private String name;

    private String profileImageUrl;
    private String thumbnailImageUrl;

    @Column(length = 100)
    private String description;

    @Builder
    public User(Long externalId) {
        this.externalId = externalId;
    }
}
