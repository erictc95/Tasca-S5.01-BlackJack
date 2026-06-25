package com.blackjack;

import com.blackjack.application.usecases.*;
import com.blackjack.domain.model.DeckType;
import com.blackjack.domain.model.Game;
import com.blackjack.domain.model.GameMode;
import com.blackjack.infrastructure.web.controller.GameController;
import com.blackjack.infrastructure.web.dto.GameResponse;
import com.blackjack.infrastructure.web.mapper.GameResponseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @MockitoBean
    private CreateGameUseCase createGameUseCase;

    @MockitoBean
    private GetGameUseCase getGameUseCase;

    @MockitoBean
    private PlayerHitUseCase playerHitUseCase;

    @MockitoBean
    private StandUseCase standUseCase;

    @MockitoBean
    private DeleteGameUseCase deleteGameUseCase;

    @MockitoBean
    private GameResponseMapper gameResponseMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateGame() throws Exception {

        UUID gameId = UUID.randomUUID();

        when(createGameUseCase.createGame(
                GameMode.COVERED,
                DeckType.SINGLE_DECK))
                .thenReturn(gameId);

        mockMvc.perform(post("/blackjack")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "gameMode": "COVERED",
                              "deckType": "SINGLE_DECK"
                            }
                            """))
                .andExpect(status().isCreated())
                .andExpect(content().string("\"" + gameId + "\""));
    }

    @Test
    void shouldDeleteGame() throws Exception {
        UUID gameId = UUID.randomUUID();

        mockMvc.perform(delete("/blackjack/{id}", gameId))
                .andExpect(status().isNoContent());

        verify(deleteGameUseCase).deleteGame(gameId);
    }

    @Test
    void shouldGetGameById() throws Exception {
        UUID gameId = UUID.randomUUID();

        Game game = new Game(
                GameMode.COVERED,
                DeckType.SINGLE_DECK
        );

        when(getGameUseCase.getGame(gameId))
                .thenReturn(game);

        when(gameResponseMapper.toResponse(game))
                .thenReturn(new GameResponse());

        mockMvc.perform(get("/blackjack/{id}", gameId))
                .andExpect(status().isOk());

        verify(getGameUseCase).getGame(gameId);
        verify(gameResponseMapper).toResponse(game);
    }

    @Test
    void shouldPlayerHit() throws Exception {

        UUID gameId = UUID.randomUUID();

        Game game = new Game(
                GameMode.COVERED,
                DeckType.SINGLE_DECK
        );

        when(playerHitUseCase.playerHit(gameId))
                .thenReturn(game);

        when(gameResponseMapper.toResponse(game))
                .thenReturn(new GameResponse());

        mockMvc.perform(post("/blackjack/{id}/hit", gameId))
                .andExpect(status().isOk());

        verify(playerHitUseCase).playerHit(gameId);
        verify(gameResponseMapper).toResponse(game);
    }

    @Test
    void shouldPlayerStand() throws Exception {

        UUID gameId = UUID.randomUUID();

        Game game = new Game(
                GameMode.COVERED,
                DeckType.SINGLE_DECK
        );

        when(standUseCase.playerStand(gameId))
                .thenReturn(game);

        when(gameResponseMapper.toResponse(game))
                .thenReturn(new GameResponse());

        mockMvc.perform(post("/blackjack/{id}/stand", gameId))
                .andExpect(status().isOk());

        verify(standUseCase).playerStand(gameId);
        verify(gameResponseMapper).toResponse(game);
    }
}
