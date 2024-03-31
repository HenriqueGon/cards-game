package com.example.cardgame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.cardgame.models.Card;
import com.example.cardgame.models.Deck;
import com.example.cardgame.models.Game;
import com.example.cardgame.models.Player;
import com.example.cardgame.repositories.CardRepository;
import com.example.cardgame.repositories.DeckRepository;
import com.example.cardgame.repositories.GameRepository;
import com.example.cardgame.repositories.PlayerRepository;

@Service
@Transactional
public class GameService {

  private GameRepository gameRepository;
  private DeckRepository deckRepository;
  private PlayerRepository playerRepository;
  private CardRepository cardRepository;

  private DeckService deckService;
  private GameService gameService;
  private PlayerService playerService;

  public Game find(UUID gameUuid) {
    return gameRepository.findOneByUuid(gameUuid).orElseThrow();
  }

  public List<Game> findAll() {
    return gameRepository.findAll();
  }

  public List<Player> findAllPlayers(UUID gameUuid) {
    return gameRepository.findAllPlayersByUuid(gameUuid);
  }

  public Game create() {
    Game createdGame = new Game();

    return gameRepository.save(createdGame);
  }

  public Deck addDeck(UUID gameUuid) {
    Deck deck = deckService.addDeck(gameUuid);

    Game game = gameRepository.findOneByUuid(gameUuid).orElseThrow();

    deck.setGame(game);

    deckRepository.save(deck);

    return deck;
  }

  public Player addPlayer(UUID gameUuid, String nickname) {
    Game foundGame = gameRepository.findOneByUuid(gameUuid).orElseThrow();
    Player player = playerRepository.save(new Player(foundGame, nickname));

    foundGame.getPlayers().add(player);

    gameRepository.save(foundGame);

    return player;
  }

  public void deletePlayer(UUID gameUuid, Long id) {
    Game game = gameService.find(gameUuid);
    Player player = playerService.find(id);

    if (player.getGame().getUuid() != game.getUuid()) {
      throw new Error("The player is not in this game");
    }

    playerRepository.delete(player);
  }

  public List<Card> dealCard(Long playerId, UUID gameUuid) {
		Player player = playerRepository.findById(playerId).orElseThrow();
		Game game = player.getGame();

    List<Card> cardsToDeal = new ArrayList<>();

		for (Deck deck : game.getDecks()) {
      for (Card card : deck.getCards()) {
        card.setPlayer(player);
        card.setIndex(null);
        cardsToDeal.add(card);

        cardRepository.save(card);
      }
    }

    return cardsToDeal;
	}

  public void shuffle(UUID gameUuid) {
		int currentPosition = 1;

    Random rand = new Random();

    Game game = gameService.find(gameUuid);

    List<Card> cards = new ArrayList<>();

    for (Deck deck : game.getDecks()) {
      for (Card card : deck.getCards()) {
        cards.add(card);
      }
    }

		while (cards.size() > 0) {
			int nextIndex = rand.nextInt(cards.size());
			Card card = cards.remove(nextIndex);
			card.setIndex(currentPosition);
			cards.add(card);
			currentPosition++;
		}
		cardRepository.saveAll(cards);
  }
  
}
