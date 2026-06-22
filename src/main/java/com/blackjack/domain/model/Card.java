package com.blackjack.domain.model;


import lombok.Getter;

@Getter
public class Card {

    private Rank rank;
    private Suit suit;

    public Card() {
    }

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue() {
        return rank.getValue();
    }
}
