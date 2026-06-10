# Blackjack Backend

## Project Overview

This project consists of a REST API developed with Spring Boot that implements a single-player Blackjack game against a dealer.

The main objective of the project is not only to build a functional game, but also to apply Domain-Oriented Design principles, maintain a rich domain model, and separate business rules from infrastructure concerns.

The application uses two different persistence technologies:

* MongoDB as the source of truth for the game state.
* MySQL for derived data such as statistics, rankings, and game summaries.

---

## User Stories

### US-01 Create a New Blackjack Game

**As a player**, I want to create a new Blackjack game, so that I can start playing against the dealer.

#### Acceptance Criteria

* A new game is created.
* The deck is shuffled.
* The player receives two cards.
* The dealer receives two cards.
* The game status is set to `IN_PROGRESS`.
* The game state is stored in MongoDB.

---

### US-02 View Game State

**As a player**, I want to view the current state of my game, so that I can see my cards and game progress.

#### Acceptance Criteria

* The player can retrieve a game by its ID.
* The player's cards are visible.
* The dealer's hidden card is not visible while the game is in progress.
* The game status is displayed.

---

### US-03 Request a Card (Hit)

**As a player**, I want to request an additional card, so that I can try to get closer to 21.

#### Acceptance Criteria

* A new card is added to the player's hand.
* The game state is updated and persisted.
* If the player's score exceeds 21, the game ends immediately.
* A result is calculated when the game finishes.

---

### US-04 Stand and Resolve the Game

**As a player**, I want to stand, so that the dealer can play and the final result can be determined.

#### Acceptance Criteria

* The player can no longer draw cards.
* The dealer draws cards until reaching at least 17 points.
* The final winner is determined.
* The game status is updated.
* A GameFinished event is published.

---

### US-05 View Game Result

**As a player**, I want to view the final result of a finished game, so that I know whether I won, lost, or drew.

#### Acceptance Criteria

* The final result is available.
* The player's final score is displayed.
* The dealer's final score is displayed.
* All dealer cards become visible.

---

### US-06 View Ranking and Statistics

**As a user**, I want to view aggregated game statistics, so that I can analyze game results.

#### Acceptance Criteria

* Statistics are retrieved from MySQL.
* Total games played are displayed.
* Total wins are displayed.
* Total losses are displayed.
* Total draws are displayed.

---

### US-07 Persist Game State

**As a player**, I want my game state to be persisted, so that I can continue the game from its exact previous state.

#### Acceptance Criteria

* The deck order is preserved.
* All dealt cards are preserved.
* The current game status is preserved.
* The game can be reconstructed exactly as it was.

---

## Blackjack Rules

The game follows a simplified Blackjack implementation:

* One player against one dealer.
* No betting system.
* Both player and dealer receive two cards at the beginning.
* The player can request cards while the game remains active.
* If the player's score exceeds 21, the game ends immediately.
* When the player stands, the dealer starts playing.
* The dealer must draw cards until reaching at least 17 points.
* The closest score to 21 wins.
* If both scores are equal, the game ends in a draw.

---

## Domain Model

The application is centered around a rich domain model.

### Main Aggregate

`Game`

The Game aggregate is responsible for maintaining consistency and enforcing Blackjack rules.

### Core Domain Objects

#### Game

Responsibilities:

* Manage game lifecycle.
* Execute player actions.
* Execute dealer actions.
* Determine game results.
* Maintain game consistency.

#### Deck

Responsibilities:

* Store remaining cards.
* Shuffle cards.
* Provide cards when requested.

#### Hand

Responsibilities:

* Store cards belonging to a participant.
* Calculate current score.
* Detect bust situations.

#### Card

Responsibilities:

* Represent a playing card.
* Store rank and suit information.

#### GameStatus

Represents the current game state.

Examples:

* IN_PROGRESS
* PLAYER_WON
* DEALER_WON
* DRAW
* PLAYER_BUST

#### GameResult

Represents the final outcome of a completed game.

---

## Initial Domain Structure

```text
Game
│
├── Deck
│
├── PlayerHand
│
├── DealerHand
│
├── GameStatus
│
└── GameResult
```

---

## Persistence Strategy

### MongoDB

MongoDB stores the complete state of an active game.

Examples:

* Deck order.
* Remaining cards.
* Player hand.
* Dealer hand.
* Game status.

MongoDB acts as the source of truth for the game.

### MySQL

MySQL stores derived information.

Examples:

* Game summaries.
* Statistics.
* Rankings.
* Historical reports.

MySQL is not used to reconstruct a game.

---

## Domain Event

### GameFinishedEvent

The minimum required domain event.

Triggered when a game reaches a final state.

Possible uses:

* Generate game summary.
* Update statistics.
* Update rankings.
* Persist derived information into MySQL.

---

## Planned Architecture

```text
src/main/java/com/blackjack

domain
│
├── model
├── events
└── ports

application
│
└── usecases

infrastructure
│
├── persistence
│   ├── mongo
│   └── mysql
│
├── web
│
└── events
```

### Architectural Principles

* Domain must not depend on Spring.
* Business rules belong to the domain model.
* Persistence must not dictate domain design.
* Application layer coordinates use cases.
* Infrastructure contains technical implementations.

---

## Planned Endpoints

### Create Game

```http
POST /games
```

### Get Game State

```http
GET /games/{id}
```

### Request Card

```http
POST /games/{id}/hit
```

### Stand

```http
POST /games/{id}/stand
```

### Get Statistics

```http
GET /ranking
```

---

## Testing Strategy

### Unit Tests

Focus on domain behavior:

* Score calculation.
* Bust detection.
* Dealer logic.
* Winner determination.
* Game transitions.

### Integration Tests

Focus on API behavior:

* Create game.
* Hit.
* Stand.
* Retrieve game state.
* Retrieve ranking.

### Coverage Goal

Minimum test coverage target:

```text
60%+
```

while prioritizing meaningful tests over coverage numbers alone.
