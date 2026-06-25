package com.blackjack;

import com.blackjack.application.usecases.CreateGameUseCase;
import com.blackjack.application.usecases.CreateGameUseCaseImpl;
import com.blackjack.domain.model.DeckType;
import com.blackjack.domain.model.Game;
import com.blackjack.domain.model.GameMode;
import com.blackjack.domain.ports.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CreateGameUseImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private CreateGameUseCaseImpl createGameUseCase;

    @Test
    void shouldCreateGameSuccessfully() {

        UUID gameId = createGameUseCase.createGame(
                GameMode.COVERED,
                DeckType.SINGLE_DECK
        );

        assertNotNull(gameId);

        ArgumentCaptor<Game> gameCaptor = ArgumentCaptor.forClass(Game.class);

        verify(gameRepository).save(gameCaptor.capture());

        Game savedGame = gameCaptor.getValue();

        assertEquals(gameId, savedGame.getId());
        assertEquals(GameMode.COVERED, savedGame.getGameMode());
        assertEquals(DeckType.SINGLE_DECK, savedGame.getDeckType());

    }

}
