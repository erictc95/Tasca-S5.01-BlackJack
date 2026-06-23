package com.blackjack;

import com.blackjack.application.usecases.*;
import com.blackjack.domain.model.DeckType;
import com.blackjack.domain.model.GameMode;
import com.blackjack.infrastructure.web.controller.GameController;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
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
}
