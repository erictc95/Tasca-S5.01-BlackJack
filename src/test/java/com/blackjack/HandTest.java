package com.blackjack;

import com.blackjack.domain.model.Card;
import com.blackjack.domain.model.Hand;
import com.blackjack.domain.model.Rank;
import com.blackjack.domain.model.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class HandTest {

    private final Card card1 = new Card(Rank.FIVE, Suit.HEARTS);
    private final Card card2 = new Card(Rank.KING, Suit.DIAMONDS);
    private final Card card3 = new Card(Rank.SEVEN, Suit.SPADES);

    @Test
    void shouldAddCardToHand() {
        Hand hand = new Hand();

        hand.addCard(card1);

        assertEquals(1, hand.getCards().size());
    }

    @Test
    void shouldCalculateScoreCorrectly() {
        Hand hand = new Hand();

        hand.addCard(card1);
        hand.addCard(card2);

        assertEquals(15, hand.calculateScore());
    }

    @Test
    void shouldDetectBust() {
        Hand bustHand = new Hand();
        Hand safeHand = new Hand();

        bustHand.addCard(card1);
        bustHand.addCard(card2);
        bustHand.addCard(card3);

        safeHand.addCard(card1);
        safeHand.addCard(card2);

        assertTrue(bustHand.isBust());
        assertFalse(safeHand.isBust());
    }

}
