package com.blackjack.domain.ports;

import com.blackjack.domain.model.Game;

import java.util.UUID;

public interface GameRepository {

    void save(Game game);

    Game findById(UUID id);

    void delete(UUID id);
}
