package com.blackjack.domain.model;

import java.util.UUID;

public class Game {

    private UUID id;
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private GameStatus status;
    private GameMode gameMode;
    private DeckType deckType;

    public Game(GameMode gameMode, DeckType deckType) {
        this.id = UUID.randomUUID();
        this.deck = new Deck(deckType);
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.status = GameStatus.IN_PROGRESS;
        this.gameMode = gameMode;
        this.deckType = deckType;
        initializeGame();
    }

    public Game(UUID id, Deck deck, Hand playerHand, Hand dealerHand, GameStatus status, GameMode gameMode, DeckType deckType) {
        this.id = id;
        this.deck = deck;
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.status = status;
        this.gameMode = gameMode;
        this.deckType = deckType;
    }

    public UUID getId() {
        return id;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public GameStatus getStatus() {
        return status;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public DeckType getDeckType() {
        return deckType;
    }

    private void initializeGame() {
        drawInitialHands();
    }

    private void drawInitialHands() {
        playerHand.addCard(deck.draw());
        dealerHand.addCard(deck.draw());
        playerHand.addCard(deck.draw());
        dealerHand.addCard(deck.draw());
    }

    public void playerHit() {
        playerHand.addCard(deck.draw());
        if (playerHand.isBust()) {
            status = GameStatus.DEALER_WON;
        }
    }

    public void stand() {
        dealerTurn();
        determineWinner();
    }

    private void dealerTurn() {
        while (dealerHand.calculateScore() < 17) {
            dealerHand.addCard(deck.draw());
        }
    }

    private void determineWinner() {
        int playerScore = playerHand.calculateScore();
        int dealerScore = dealerHand.calculateScore();

        if (dealerHand.isBust()) {
            status = GameStatus.PLAYER_WON;
        } else if (dealerScore > playerScore) {
            status = GameStatus.DEALER_WON;
        } else if (playerScore > dealerScore) {
            status = GameStatus.PLAYER_WON;
        } else {
            status = GameStatus.DRAW;
        }
    }


}
