package com.blackjack.application.usecases;

import com.blackjack.domain.model.GameStatus;
import com.blackjack.infrastructure.persistence.mysql.repository.GameSummaryJpaRepository;
import com.blackjack.infrastructure.web.dto.RankingResponse;
import org.springframework.stereotype.Service;

@Service
public class GetRankingUseCaseImpl implements GetRankingUseCase {

    private final GameSummaryJpaRepository repository;

    public GetRankingUseCaseImpl(GameSummaryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RankingResponse execute() {

        long gamesPlayed = repository.count();

        long playerWins = repository.countByResult(GameStatus.PLAYER_WON);
        long dealerWins = repository.countByResult(GameStatus.DEALER_WON);
        long draws = repository.countByResult(GameStatus.DRAW);

        double winRate = gamesPlayed == 0
                ? 0
                : Math.round((playerWins * 100.0 / gamesPlayed) * 100.0) / 100.0;

        return new RankingResponse(
                gamesPlayed,
                playerWins,
                dealerWins,
                draws,
                winRate
        );
    }
}
