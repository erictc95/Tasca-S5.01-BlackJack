package com.blackjack.application.usecases;

import com.blackjack.domain.model.Game;
import com.blackjack.domain.ports.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetGameUseCaseImpl implements GetGameUseCase{

    private final GameRepository gameRepository;

    public GetGameUseCaseImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Override
    public Game getGame(UUID id) {
        return gameRepository.findById(id);
    }
}
