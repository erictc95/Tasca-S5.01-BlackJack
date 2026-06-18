package com.blackjack.application.usecases;

import com.blackjack.domain.model.DeckType;
import com.blackjack.domain.model.GameMode;

import java.util.UUID;

public interface CreateGameUseCase {

    UUID createGame(GameMode gameMode, DeckType deckType);
}
