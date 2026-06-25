package com.blackjack;

import com.blackjack.application.usecases.PlayerHitUseCaseImpl;
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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerHitUseCaseImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private PlayerHitUseCaseImpl playerHitUseCase;

    @Test
    void shouldPlayerHitSuccessfully() {

        UUID gameId = UUID.randomUUID();

        Game game = spy(new Game(
                GameMode.COVERED,
                DeckType.SINGLE_DECK
        ));

        when(gameRepository.findById(gameId))
                .thenReturn(game);

        Game result = playerHitUseCase.playerHit(gameId);

        verify(gameRepository).findById(gameId);
        verify(game).playerHit();
        verify(gameRepository).save(game);

        assertSame(game, result);
    }
}
