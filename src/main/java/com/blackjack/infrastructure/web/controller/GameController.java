package com.blackjack.infrastructure.web.controller;

import com.blackjack.application.usecases.*;
import com.blackjack.domain.model.Game;
import com.blackjack.domain.ports.GameRepository;
import com.blackjack.infrastructure.web.dto.CreateGameRequest;
import com.blackjack.infrastructure.web.dto.GameResponse;
import com.blackjack.infrastructure.web.dto.RankingResponse;
import com.blackjack.infrastructure.web.mapper.GameResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Blackjack API", description = "Blackjack game operations")
@RestController
@RequestMapping("/blackjack")
public class GameController {
    private final CreateGameUseCase createGameUseCase;
    private final GetGameUseCase getGameUseCase;
    private final PlayerHitUseCase playerHitUseCase;
    private final StandUseCase standUseCase;
    private final DeleteGameUseCase deleteGameUseCase;
    private final GameResponseMapper gameResponseMapper;
    private final GetRankingUseCase getRankingUseCase;


    public GameController(CreateGameUseCase createGameUseCase, GetGameUseCase getGameUseCase, PlayerHitUseCase playerHitUseCase, StandUseCase standUseCase, DeleteGameUseCase deleteGameUseCase, GameResponseMapper gameResponseMapper, GetRankingUseCase getRankingUseCase) {
        this.createGameUseCase = createGameUseCase;
        this.getGameUseCase = getGameUseCase;
        this.playerHitUseCase = playerHitUseCase;
        this.standUseCase = standUseCase;
        this.deleteGameUseCase = deleteGameUseCase;
        this.gameResponseMapper = gameResponseMapper;
        this.getRankingUseCase = getRankingUseCase;
    }

    @Operation(summary = "Get a game by ID")
    @GetMapping("/{id}")
    public GameResponse getGameById(@PathVariable UUID id) {
        Game game = getGameUseCase.getGame(id);
        return gameResponseMapper.toResponse(game);
    }

    @Operation(summary = "Create a new game")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createGame(@Valid @RequestBody CreateGameRequest request) {
        return createGameUseCase.createGame(request.getGameMode(), request.getDeckType());
    }

    @Operation(summary = "Player draws a card")
    @PostMapping("/{id}/hit")
    public GameResponse playerHit(@PathVariable UUID id) {
        Game game = playerHitUseCase.playerHit(id);
        return gameResponseMapper.toResponse(game);
    }

    @Operation(summary = "Player stands")
    @PostMapping("/{id}/stand")
    public GameResponse playerStand(@PathVariable UUID id) {
        Game game = standUseCase.playerStand(id);
        return gameResponseMapper.toResponse(game);
    }

    @Operation(summary = "Delete a game")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable UUID id) {
        deleteGameUseCase.deleteGame(id);
    }

    @Operation(summary = "Get ranking statistics")
    @GetMapping("/ranking")
    @ResponseStatus(HttpStatus.OK)
    public RankingResponse getRanking() {
        return getRankingUseCase.execute();
    }
}
