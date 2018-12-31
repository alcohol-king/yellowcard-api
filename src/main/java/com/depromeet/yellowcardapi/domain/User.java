package com.depromeet.yellowcardapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Lob
    private String profileImageUrl;

    @Lob
    private String thumbnailImageUrl;

    @Column(length = 100)
    private String statusMessage;

    @ManyToMany
    @JoinTable(
            name = "drink_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id")
    )
    private Set<Drink> likedDrinks = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<History> drinkHistories = new HashSet<>();

    @Builder
    public User(Long externalId, String name, String profileImageUrl,
                String thumbnailImageUrl, String statusMessage) {
        this.externalId = externalId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.statusMessage = statusMessage;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long addHistory(History history) {
        Long result = historyIsExists(history.getUserId(), history.getDrunkAt());

        if (result != -1) {
            drinkHistories.add(history);
        }
        return result;
    }

    private Long historyIsExists(Long userId, LocalDate drunkAt) {
        for (History history : drinkHistories) {
            if (history.getUserId().compareTo(userId) == 0
                    && history.getDrunkAt().compareTo(drunkAt) == 0) {
                return history.getId();
            }
        }
        return (long) -1;
    }
}
