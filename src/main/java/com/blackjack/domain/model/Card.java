package com.blackjack.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {

    private final Rank rank;
    private final Suit suit;

    public int getValue() {
        return rank.getValue();
    }
}
