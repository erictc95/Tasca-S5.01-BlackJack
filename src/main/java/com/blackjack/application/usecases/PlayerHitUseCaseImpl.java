package com.blackjack.application.usecases;

import com.blackjack.domain.model.Game;
import com.blackjack.domain.ports.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerHitUseCaseImpl implements PlayerHitUseCase{

    private final GameRepository gameRepository;

    public PlayerHitUseCaseImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game playerHit(UUID id) {
        Game game = gameRepository.findById(id);
        game.playerHit();
        gameRepository.save(game);
        return game;
    }


}
