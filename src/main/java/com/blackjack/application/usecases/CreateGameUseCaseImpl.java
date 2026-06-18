package com.blackjack.application.usecases;

import com.blackjack.domain.model.DeckType;
import com.blackjack.domain.model.Game;
import com.blackjack.domain.model.GameMode;
import com.blackjack.domain.ports.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateGameUseCaseImpl implements CreateGameUseCase{

    private final GameRepository gameRepository;

    public CreateGameUseCaseImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public UUID createGame(GameMode gameMode, DeckType deckType) {

        Game game = new Game(gameMode, deckType);

        gameRepository.save(game);

        return game.getId();
    }
}
