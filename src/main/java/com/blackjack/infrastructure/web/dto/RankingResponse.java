package com.blackjack.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankingResponse {

    private long gamesPlayed;

    private long playerWins;

    private long dealerWins;

    private long draws;

    private double winRate;
}
