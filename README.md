# 🃏 Blackjack REST API

## 📖 Project Overview

This project is a RESTful API developed in **Java 21** using **Spring Boot** that implements a complete single-player Blackjack game against the dealer.

The application was designed following **Domain-Driven Design (DDD)** principles and a **Rich Domain Model**, where the business rules are encapsulated inside the domain instead of controllers or services.

The architecture follows a **Hexagonal / Clean Architecture** approach, separating the project into three main layers:

- **Domain** – Business rules and game logic.
- **Application** – Use cases that coordinate application flow.
- **Infrastructure** – REST API, persistence, database access and external technologies.

The application uses **two different databases with different responsibilities**:

- **MongoDB** stores the complete game state and acts as the source of truth.
- **MySQL** stores derived information such as game summaries and ranking statistics.

The project also includes automated tests, Swagger documentation, Docker support and domain events.

---

# 🚀 Technologies

- Java 21
- Spring Boot 3.5
- Spring Web
- Spring Data MongoDB
- Spring Data JPA
- MongoDB
- MySQL
- Docker
- Maven
- Lombok
- Swagger / OpenAPI
- JUnit 5
- Mockito
- JaCoCo

---

# ✨ Main Features

- Create a new Blackjack game
- Retrieve an existing game
- Draw a new card (Hit)
- Stand and let the dealer play
- Automatic winner calculation
- Persistent game state
- Hidden dealer card while the game is in progress
- Complete game history stored in MongoDB
- Automatic game summary generation in MySQL
- Ranking and statistics endpoint
- Global exception handling
- Swagger API documentation
- Automated tests with more than **70% code coverage**

---

# 🏛️ Architecture

The project follows a layered architecture inspired by **Hexagonal Architecture**.

```text
                 REST API
                     │
                     ▼
           Infrastructure Layer
                     │
                     ▼
          Application Layer
              (Use Cases)
                     │
                     ▼
              Domain Layer
          (Business Rules)
                     │
                     ▼
          Persistence Adapters
        MongoDB          MySQL
```

Each layer has a single responsibility.

### Domain

Contains all Blackjack business rules.

The domain has **no dependency on Spring Boot** or any persistence framework.

### Application

Coordinates the use cases.

Examples:

- Create Game
- Get Game
- Hit
- Stand
- Delete Game
- Get Ranking

Application services do not contain game rules.

### Infrastructure

Contains technical implementations:

- REST Controllers
- MongoDB repositories
- MySQL repositories
- Event publishers
- DTOs
- Mappers
- Exception handlers

---

# 📂 Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.blackjack
│   │
│   ├── application
│   │   ├── events
│   │   └── usecases
│   │
│   ├── domain
│   │   ├── events
│   │   ├── exceptions
│   │   ├── model
│   │   └── ports
│   │
│   └── infrastructure
│       ├── events
│       ├── persistence
│       │   ├── mongo
│       │   └── mysql
│       └── web
│           ├── controller
│           ├── dto
│           ├── exception
│           └── mapper
│
└── test
```

---

# 🎯 Design Goals

The project was designed to satisfy the following objectives:

- Rich Domain Model
- Clean Architecture
- Separation of concerns
- Testability
- Persistent game state
- Reproducible game flow
- Hidden information protection
- Easy extensibility
- Independent persistence technologies

# 🎮 Blackjack Rules

The application implements a simplified version of Blackjack for a single player against the dealer.

## Game Flow

1. A new game is created.
2. The deck is shuffled.
3. The player receives two cards.
4. The dealer receives two cards.
5. Only one dealer card is visible while the game is in progress.
6. The player can repeatedly choose:
   - **Hit** (draw another card)
   - **Stand** (finish the turn)
7. If the player exceeds 21 points, the game immediately ends.
8. When the player stands, the dealer automatically plays.
9. The dealer must draw cards until reaching **17 or more points**.
10. The winner is determined.

---

## Winner Rules

### Player Wins

The player wins when:

- The dealer busts.
- The player's score is higher than the dealer's score.
- The player has Blackjack and the dealer does not.

---

### Dealer Wins

The dealer wins when:

- The player busts.
- The dealer's score is higher.

---

### Draw

The game ends in a draw when both player and dealer finish with the same score.

---

## Dealer Hidden Information

To prevent cheating, the API never exposes information that the player should not know.

While the game is in progress:

- The complete deck is never returned.
- Remaining cards are never exposed.
- Only one dealer card is visible.

When the game finishes:

- All dealer cards become visible.
- Final scores are returned.
- The game result is available.

---

# 🧩 Rich Domain Model

The project follows a Rich Domain Model approach.

Business rules are implemented inside domain entities instead of controllers or application services.

## Main Aggregate

### Game

The `Game` aggregate is the central element of the application.

It is responsible for:

- Starting a new game.
- Managing player actions.
- Managing dealer actions.
- Controlling game state.
- Calculating the final result.
- Preventing invalid actions.
- Maintaining consistency.

---

## Domain Objects

### Deck

Responsibilities:

- Generate cards.
- Shuffle cards.
- Draw cards.
- Preserve remaining cards.

---

### Hand

Responsibilities:

- Store player cards.
- Store dealer cards.
- Calculate scores.
- Handle Ace value automatically.
- Detect bust situations.

---

### Card

Represents an immutable playing card.

Stores:

- Rank
- Suit
- Value

---

### GameStatus

Represents the current game state.

Possible values:

- IN_PROGRESS
- PLAYER_WON
- DEALER_WON
- DRAW
- PLAYER_BUST

---

### DeckType

Defines the number of decks used:

- SINGLE_DECK
- DOUBLE_DECK
- EIGHT_DECKS

---

### GameMode

Defines how dealer cards are displayed.

Supported modes:

- COVERED
- UNCOVERED

---

# 💾 Persistence Strategy

The application uses two databases with different responsibilities.

## MongoDB

MongoDB stores the complete game.

It acts as the **source of truth**.

Stored information includes:

- Game ID
- Complete deck
- Remaining cards
- Player hand
- Dealer hand
- Current status
- Game mode
- Deck type

Because the full deck is persisted, the game can always continue exactly where it stopped.

---

## MySQL

MySQL stores only **derived information**.

It never stores the complete game.

Examples:

- Finished games
- Final scores
- Game summaries
- Statistics
- Ranking information

This separation avoids duplicating responsibilities between databases.

---

# 📢 Domain Event

The project implements a domain event:

## GameFinishedEvent

The event is published every time a game reaches a final state.

It contains:

- Game ID
- Final result
- Player score
- Dealer score
- Finish date

The event is handled by an infrastructure publisher that stores a game summary inside MySQL.

This keeps the domain independent from persistence technologies while allowing derived information to be generated automatically.

---

# 🔄 Game Flow

```text
Create Game
      │
      ▼
MongoDB
(Store complete game)
      │
      ▼
Player Hit / Stand
      │
      ▼
Game Finished
      │
      ▼
GameFinishedEvent
      │
      ▼
MySQL
(Store game summary)
      │
      ▼
GET /ranking
```

## 👤 Author

Eric Tarres Cabrisas - GitHub --> erictc95
