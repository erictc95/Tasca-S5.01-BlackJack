package com.blackjack.infrastructure.persistence.mongo;

import com.blackjack.domain.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Setter
@Getter
@Document(collection = "games")
public class GameDocument {

    @Id
    private UUID id;

    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private GameStatus status;
    private GameMode gameMode;
    private DeckType deckType;

    public GameDocument() {
    }

    public GameDocument(UUID id, Deck deck, Hand playerHand, Hand dealerHand, GameStatus status, GameMode gameMode, DeckType deckType) {
        this.id = id;
        this.deck = deck;
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.status = status;
        this.gameMode = gameMode;
        this.deckType = deckType;
    }


}
