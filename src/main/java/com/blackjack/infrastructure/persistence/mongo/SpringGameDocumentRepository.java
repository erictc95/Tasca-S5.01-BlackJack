package com.blackjack.infrastructure.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SpringGameDocumentRepository extends MongoRepository<GameDocument, UUID> {
}
