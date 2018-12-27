package com.depromeet.yellowcardapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@NoArgsConstructor
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

    @ManyToMany
    @JoinTable(
            name = "drink_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id")
    )
    private Set<Drink> likedDrinks = new HashSet<>();

    @Builder
    public User(Long externalId) {
        this.externalId = externalId;
    }

    public boolean likeDrink(Drink drink) {
        if (alreadyLikedDrink(drink.getId())) {
            return false;
        }

        likedDrinks.add(drink);
        return true;
    }

    private boolean alreadyLikedDrink(Long drinkId) {
        for (Drink drink : likedDrinks) {
            if (drink.getId().compareTo(drinkId) == 0) {
                return true;
            }
        }
        return false;
    }
}
