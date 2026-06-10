package com.blackjack.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        Collections.shuffle(cards);
    }

}
