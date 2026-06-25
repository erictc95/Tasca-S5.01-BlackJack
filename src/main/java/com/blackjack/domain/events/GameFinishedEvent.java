package com.blackjack.domain.events;

import com.blackjack.domain.model.GameStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class GameFinishedEvent {

    private final UUID gameId;
    private final GameStatus result;
    private final int playerScore;
    private final int dealerScore;
    private final LocalDateTime finishedAt;

    public GameFinishedEvent(UUID gameId,
                             GameStatus result,
                             int playerScore,
                             int dealerScore,
                             LocalDateTime finishedAt) {

        this.gameId = gameId;
        this.result = result;
        this.playerScore = playerScore;
        this.dealerScore = dealerScore;
        this.finishedAt = finishedAt;
    }
}
