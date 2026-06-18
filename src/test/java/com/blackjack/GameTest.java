package com.blackjack;

import com.blackjack.domain.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    void shouldStartGameWithTwoCardsForPlayerAndDealer() {

        Game game = new Game(GameMode.COVERED, DeckType.SINGLE_DECK);

        assertEquals(2, game.getPlayerHand().getCards().size());
        assertEquals(2, game.getDealerHand().getCards().size());
    }

    @Test
    void shouldStartGameInProgress() {
        Game game = new Game(GameMode.UNCOVERED, DeckType.DOUBLE_DECK);

        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());
    }

    @Test
    void shouldAddCardToPlayerHandWhenPlayerHits() {
        Game game = new Game(GameMode.UNCOVERED, DeckType.SINGLE_DECK);

        int previousPlayerCardsCount = game.getPlayerHand().getCards().size();
        game.playerHit();
        int actuallyPlayerCardsCount = game.getPlayerHand().getCards().size();

        assertEquals(previousPlayerCardsCount + 1, actuallyPlayerCardsCount);
    }

    @Test
    void shouldSetStatusToDealerWonWhenPlayerBusts() {

    }


}
