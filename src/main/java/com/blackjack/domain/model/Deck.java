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
        createDeck(deckType);
        shuffleDeck();
    }

    private void createDeck(DeckType deckType) {
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

    private Card draw() {
        return cards.removeFirst();
    }

    private int remainingCards() {
        return cards.size();
    }

}
