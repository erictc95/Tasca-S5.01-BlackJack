package com.blackjack.domain.exceptions;

import java.util.UUID;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(UUID id) {
        super("Game with id: " + id + " not found");
    }
}
