package com.blackjack.infrastructure.web.controller;

import com.blackjack.application.usecases.CreateGameUseCase;
import com.blackjack.application.usecases.GetGameUseCase;
import com.blackjack.application.usecases.PlayerHitUseCase;
import com.blackjack.application.usecases.StandUseCase;
import com.blackjack.domain.model.Game;
import com.blackjack.domain.ports.GameRepository;
import com.blackjack.infrastructure.web.dto.CreateGameRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/blackjack/")
public class GameController {
    private final CreateGameUseCase createGameUseCase;
    private final GetGameUseCase getGameUseCase;
    private final PlayerHitUseCase playerHitUseCase;
    private final StandUseCase standUseCase;


    public GameController(CreateGameUseCase createGameUseCase, GetGameUseCase getGameUseCase, PlayerHitUseCase playerHitUseCase, StandUseCase standUseCase) {
        this.createGameUseCase = createGameUseCase;
        this.getGameUseCase = getGameUseCase;
        this.playerHitUseCase = playerHitUseCase;
        this.standUseCase = standUseCase;
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable UUID id) {
        return getGameUseCase.getGame(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createGame(@Valid @RequestBody CreateGameRequest request) {
        return createGameUseCase.createGame(request.getGameMode(), request.getDeckType());
    }

    @PostMapping("/{id}/hit")
    public Game playerHit(@PathVariable UUID id) {
        return playerHitUseCase.playerHit(id);
    }


    @PostMapping("/{id}/stand")
    public Game playerStand(@PathVariable UUID id) {
        return standUseCase.playerStand(id);
    }
}
