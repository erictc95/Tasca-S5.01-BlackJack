package com.blackjack.infrastructure.events;

import com.blackjack.application.events.GameFinishedPublisher;
import com.blackjack.domain.events.GameFinishedEvent;
import com.blackjack.infrastructure.persistence.mysql.entity.GameSummaryEntity;
import com.blackjack.infrastructure.persistence.mysql.repository.GameSummaryJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class MysqlGameFinishedPublisher implements GameFinishedPublisher {

    private final GameSummaryJpaRepository repository;

    public MysqlGameFinishedPublisher(GameSummaryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void publish(GameFinishedEvent event) {

        GameSummaryEntity summary = new GameSummaryEntity(
                event.getGameId(),
                event.getResult(),
                event.getPlayerScore(),
                event.getDealerScore(),
                event.getFinishedAt()
        );

        repository.save(summary);
    }
}
