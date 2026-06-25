package com.blackjack.infrastructure.persistence.mysql.entity;

import com.blackjack.domain.model.GameStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "game_summary")
public class GameSummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID gameId;

    @Enumerated(EnumType.STRING)
    private GameStatus result;

    private int playerScore;

    private int dealerScore;

    private LocalDateTime finishedAt;

    public GameSummaryEntity() {
    }

    public GameSummaryEntity(UUID gameId,
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
