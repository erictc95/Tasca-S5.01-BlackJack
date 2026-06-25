package com.blackjack;

import com.blackjack.domain.model.Card;
import com.blackjack.domain.model.Deck;
import com.blackjack.domain.model.DeckType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    private final List<Card> cards = new ArrayList<>();

    @Test
    void shouldCreateSingleDeckWith52Cards() {
        Deck deck = new Deck(DeckType.SINGLE_DECK);

        assertEquals( 52, deck.remainingCards());
    }

    @Test
    void shouldCreateDoubleDeckWith104Cards() {
        Deck deck = new Deck(DeckType.DOUBLE_DECK);

        assertEquals(104, deck.remainingCards());
    }

    @Test
    void shouldRemoveCardWhenDrawIsCalled() {
        Deck deck = new Deck(DeckType.SINGLE_DECK);

        deck.draw();

        assertEquals(51, deck.remainingCards());
    }

    @Test
    void shouldThrowExceptionWhenDeckIsEmpty() {
        Deck deck = new Deck(DeckType.SINGLE_DECK);

        while (deck.remainingCards() > 0) {
            deck.draw();
        }

        assertThrows(IllegalStateException.class, deck::draw);
    }
    @Test
    void shouldReturnCardWhenDrawing() {

        Deck deck = new Deck(DeckType.SINGLE_DECK);

        Card card = deck.draw();

        assertNotNull(card);
    }


}
