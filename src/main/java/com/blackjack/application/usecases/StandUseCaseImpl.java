package com.blackjack.application.usecases;

import com.blackjack.domain.model.Game;
import com.blackjack.domain.ports.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StandUseCaseImpl implements StandUseCase{

    private final GameRepository gameRepository;

    public StandUseCaseImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game playerStand(UUID id) {
        Game game = gameRepository.findById(id);
        game.stand();
        gameRepository.save(game);
        return game;
    }
}
