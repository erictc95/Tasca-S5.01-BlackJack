package com.blackjack.application.usecases;

import com.blackjack.application.events.GameFinishedPublisher;
import com.blackjack.domain.events.GameFinishedEvent;
import com.blackjack.domain.model.Game;
import com.blackjack.domain.model.GameStatus;
import com.blackjack.domain.ports.GameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StandUseCaseImpl implements StandUseCase {

    private final GameRepository gameRepository;
    private final GameFinishedPublisher gameFinishedPublisher;

    public StandUseCaseImpl(GameRepository gameRepository,
                            GameFinishedPublisher gameFinishedPublisher) {

        this.gameRepository = gameRepository;
        this.gameFinishedPublisher = gameFinishedPublisher;
    }

    @Override
    public Game playerStand(UUID id) {

        Game game = gameRepository.findById(id);

        game.stand();

        gameRepository.save(game);

        if (game.getStatus() != GameStatus.IN_PROGRESS) {

            GameFinishedEvent event = new GameFinishedEvent(
                    game.getId(),
                    game.getStatus(),
                    game.getPlayerHand().calculateScore(),
                    game.getDealerHand().calculateScore(),
                    LocalDateTime.now()
            );

            gameFinishedPublisher.publish(event);
        }

        return game;
    }
}
