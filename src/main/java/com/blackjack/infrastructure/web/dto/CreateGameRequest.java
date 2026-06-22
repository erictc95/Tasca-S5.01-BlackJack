package com.blackjack.infrastructure.web.dto;

import com.blackjack.domain.model.DeckType;
import com.blackjack.domain.model.GameMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateGameRequest {

    private GameMode gameMode;
    private DeckType deckType;
}
