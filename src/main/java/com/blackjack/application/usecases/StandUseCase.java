package com.blackjack.application.usecases;

import com.blackjack.domain.model.Game;

import java.util.UUID;

public interface StandUseCase {

    Game playerStand(UUID id);

}
