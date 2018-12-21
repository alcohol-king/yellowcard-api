package com.depromeet.yellowcardapi.user.domain.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private Long externalId;

    @Column(length = 20)
    private String name;

    private String profileImageUrl;
    private String thumbnailImageUrl;

    @Column(length = 100)
    private String description;
}
