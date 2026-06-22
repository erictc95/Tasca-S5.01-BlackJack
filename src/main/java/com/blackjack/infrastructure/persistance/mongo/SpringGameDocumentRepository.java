package com.blackjack.infrastructure.persistance.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SpringGameDocumentRepository extends MongoRepository<GameDocument, UUID> {
}
