package com.blackjack.infrastructure.web.dto;

import com.blackjack.domain.model.Card;
import com.blackjack.domain.model.DeckType;
import com.blackjack.domain.model.GameMode;
import com.blackjack.domain.model.GameStatus;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class GameResponse {

    private UUID id;
    private List<Card> playerCards;
    private List<Card> dealerCards;
    private GameStatus status;
    private GameMode gameMode;
    private DeckType deckType;

    public GameResponse() {
    }

    public GameResponse(UUID id,
                        List<Card> playerCards,
                        List<Card> dealerCards,
                        GameStatus status,
                        GameMode gameMode,
                        DeckType deckType) {

        this.id = id;
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        this.status = status;
        this.gameMode = gameMode;
        this.deckType = deckType;
    }
}
