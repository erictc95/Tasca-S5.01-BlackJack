package com.blackjack.infrastructure.persistence.mongo;

import com.blackjack.domain.exceptions.GameNotFoundException;
import com.blackjack.domain.model.Game;
import com.blackjack.domain.ports.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MongoGameRepository implements GameRepository {

    private final SpringGameDocumentRepository springRepository;

    public MongoGameRepository(SpringGameDocumentRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public void save(Game game) {
        GameDocument gameDocument = toDocument(game);

        springRepository.save(gameDocument);
    }

    @Override
    public Game findById(UUID id) {
        Optional<GameDocument> gameDocument = springRepository.findById(id);

        return toDomain(gameDocument.orElseThrow(() -> new GameNotFoundException(id)));
    }

    @Override
    public void delete(UUID id) {
        springRepository.deleteById(id);
    }

    private GameDocument toDocument(Game game) {
        return new GameDocument(
                game.getId(),
                game.getDeck(),
                game.getPlayerHand(),
                game.getDealerHand(),
                game.getStatus(),
                game.getGameMode(),
                game.getDeckType()
        );
    }

    private Game toDomain(GameDocument gameDocument) {
        return new Game(
                gameDocument.getId(),
                gameDocument.getDeck(),
                gameDocument.getPlayerHand(),
                gameDocument.getDealerHand(),
                gameDocument.getStatus(),
                gameDocument.getGameMode(),
                gameDocument.getDeckType()
        );
    }
}
