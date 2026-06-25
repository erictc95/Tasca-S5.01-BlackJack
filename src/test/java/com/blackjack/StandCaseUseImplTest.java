package com.blackjack;

import com.blackjack.application.events.GameFinishedPublisher;
import com.blackjack.application.usecases.StandUseCaseImpl;
import com.blackjack.domain.events.GameFinishedEvent;
import com.blackjack.domain.model.DeckType;
import com.blackjack.domain.model.Game;
import com.blackjack.domain.model.GameMode;
import com.blackjack.domain.ports.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StandUseCaseImplTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameFinishedPublisher gameFinishedPublisher;

    @InjectMocks
    private StandUseCaseImpl standUseCase;

    @Test
    void shouldStandSuccessfully() {

        UUID gameId = UUID.randomUUID();

        Game game = spy(new Game(
                GameMode.COVERED,
                DeckType.SINGLE_DECK
        ));

        when(gameRepository.findById(gameId))
                .thenReturn(game);

        Game result = standUseCase.playerStand(gameId);

        verify(gameRepository).findById(gameId);
        verify(game).stand();
        verify(gameRepository).save(game);

        verify(gameFinishedPublisher)
                .publish(ArgumentMatchers.any(GameFinishedEvent.class));

        assertSame(game, result);
    }
}
