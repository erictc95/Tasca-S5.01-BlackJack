package com.blackjack.infrastructure.web.mapper;

import com.blackjack.domain.model.Card;
import com.blackjack.domain.model.Game;
import com.blackjack.domain.model.GameStatus;
import com.blackjack.infrastructure.web.dto.GameResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameResponseMapper {

    public GameResponse toResponse(Game game) {

        List<Card> dealerCards;

        if (game.getStatus() == GameStatus.IN_PROGRESS) {
            dealerCards = List.of(game.getDealerHand().getCards().getFirst());
        } else {
            dealerCards = game.getDealerHand().getCards();
        }

        return new GameResponse(
                game.getId(),
                game.getPlayerHand().getCards(),
                dealerCards,
                game.getStatus(),
                game.getGameMode(),
                game.getDeckType()
        );
    }
}
