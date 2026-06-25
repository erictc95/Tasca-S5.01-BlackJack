package com.blackjack.infrastructure.persistence.mysql.repository;

import com.blackjack.domain.model.GameStatus;
import com.blackjack.infrastructure.persistence.mysql.entity.GameSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSummaryJpaRepository extends JpaRepository<GameSummaryEntity, Long> {

    long countByResult(GameStatus result);

}
