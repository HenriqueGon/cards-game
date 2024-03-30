package com.example.cardgame.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.cardgame.models.Deck;
import com.example.cardgame.models.Game;
import com.example.cardgame.models.Player;
import com.example.cardgame.repositories.DeckRepository;
import com.example.cardgame.repositories.GameRepository;
import com.example.cardgame.repositories.PlayerRepository;

@Service
@Transactional
public class GameService {

  private GameRepository gameRepository;
  private DeckRepository deckRepository;
  private PlayerRepository playerRepository;

  private DeckService deckService;

  public List<Game> findAll() {
    return gameRepository.findAll();
  }

  public Game create() {
    Game createdGame = new Game();

    return gameRepository.save(createdGame);
  }

  public Deck addDeckToGame(UUID gameUuid) {
    Deck deck = deckService.create();
    
    Game game = gameRepository.findOneByUuid(gameUuid).orElseThrow();

    deck.setGame(game);

    deckRepository.save(deck);

    return deck;
  }

  public Player addPlayerToGame(UUID gameUuid) {
    Game foundGame = gameRepository.findOneByUuid(gameUuid).orElseThrow();

    Player player = playerRepository.save(new Player(foundGame));

    return player;
  }

  public void dealCardToPlayer(Player player) {
		
	}
  
}
