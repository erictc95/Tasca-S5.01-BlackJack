package com.blackjack.application.usecases;

import com.blackjack.domain.ports.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteGameUseCaseImpl implements DeleteGameUseCase{

    private final GameRepository gameRepository;

    public DeleteGameUseCaseImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void deleteGame(UUID id) {
        gameRepository.delete(id);
    }
}
