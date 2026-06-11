package com.blackjack.domain.model;

public enum DeckType {

    SINGLE_DECK(1),
    DOUBLE_DECK(2),
    SIX_DECKS(6),
    EIGHT_DECKS(8);

    private final int numberOfDecks;

    DeckType(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }
}
