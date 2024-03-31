# Cards Game

## About the project

<p>
  This is a RESTful API for a basic card game. This project was developed as a homework assignment for a job opportunity. There is also a front-end that consumes this API. The front-end is available on: <a href="https://github.com/HenriqueGon/cards-game-front" target="_blank">https://github.com/HenriqueGon/cards-game-front</a>
</p>

### Built With

  - [Java](https://www.java.com/)
  - [Spring](https://spring.io/)
  - [H2](https://www.h2database.com/html/main.html)

## Getting Started

To get a local copy up and running follow these simple example steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/HenriqueGon/cards-game.git
   ```
2. Enter the project dir
   ```sh
   cd cards-game
   ```
3. Run Maven install
   ```sh
   mvnw install
   ```

# Features

Follows, there are a mapping of features to the API.

<b> Create a game </b>

  [POST]   http://localhost:8080/games
  [GET]    http://localhost:8080/games
  [GET]    http://localhost:8080/games/:game_uuid
  
<b> Create a deck </b>

  [POST]   http://localhost:8080/decks

<b> Add a deck to the game deck </b>

  [POST]   http://localhost:8080/games/:game_uuid/decks

<b> Get, add and remove players from a game </b>

  [POST] http://localhost:8080/games/:game_uuid/players
  [GET] http://localhost:8080/games/:game_uuid/players
  [DELETE] http://localhost:8080/games/:game_uuid/players/:player_id

<b> Deal cards to a player in a game from the game deck </b>

  [POST] http://localhost:8080/games/:game_uuid/deal-cards
  body: { playerId: number }

<b> Get the list of cards for a player </b>

  [GET] http://localhost:8080/players/:player_id/cards

<b> Shuffle the game deck </b>

  [POST] http://localhost:8080/games/:game_uuid/shuffle

# Known limitations

 - Local storage database
 - Lack of concurrency control
 - Lack of tests
