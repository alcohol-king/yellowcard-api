package com.depromeet.yellowcardapi.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    private String profileImageUrl;
    private String thumbnailImageUrl;

    @Column(length = 100)
    private String description;
}
