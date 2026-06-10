package com.blackjack.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    private void initializeDeck() {
        createDeck();
        shuffleDeck();
    }

    private void createDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    private void shuffleDeck() {

    }
}
