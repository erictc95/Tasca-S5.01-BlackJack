package com.blackjack.infrastructure.web.controller;

import com.blackjack.application.usecases.*;
import com.blackjack.domain.model.Game;
import com.blackjack.domain.ports.GameRepository;
import com.blackjack.infrastructure.web.dto.CreateGameRequest;
import com.blackjack.infrastructure.web.dto.GameResponse;
import com.blackjack.infrastructure.web.dto.RankingResponse;
import com.blackjack.infrastructure.web.mapper.GameResponseMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


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

    @GetMapping("/{id}")
    public GameResponse getGameById(@PathVariable UUID id) {
        Game game = getGameUseCase.getGame(id);
        return gameResponseMapper.toResponse(game);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createGame(@Valid @RequestBody CreateGameRequest request) {
        return createGameUseCase.createGame(request.getGameMode(), request.getDeckType());
    }

    @PostMapping("/{id}/hit")
    public GameResponse playerHit(@PathVariable UUID id) {
        Game game = playerHitUseCase.playerHit(id);
        return gameResponseMapper.toResponse(game);
    }


    @PostMapping("/{id}/stand")
    public GameResponse playerStand(@PathVariable UUID id) {
        Game game = standUseCase.playerStand(id);
        return gameResponseMapper.toResponse(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable UUID id) {
        deleteGameUseCase.deleteGame(id);
    }

    @GetMapping("/ranking")
    @ResponseStatus(HttpStatus.OK)
    public RankingResponse getRanking() {
        return getRankingUseCase.execute();
    }
}
