package com.blackjack.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>();
    private final DeckType deckType;

    public Deck(DeckType deckType) {
        this.deckType = deckType;
        initializeDeck();
    }

    private void initializeDeck() {
        createDeck();
        shuffleDeck();
    }

    private void createDeck() {
        for (int i = 0; i < deckType.getNumberOfDecks(); i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(rank, suit));
                }
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards remaining in deck");
        }
        return cards.removeFirst();
    }

    private int remainingCards() {
        return cards.size();
    }

}
