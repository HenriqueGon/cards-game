package com.example.cardgame.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardgame.models.Card;
import com.example.cardgame.models.Deck;
import com.example.cardgame.models.Game;
import com.example.cardgame.models.Player;
import com.example.cardgame.services.DeckService;
import com.example.cardgame.services.GameService;

@RestController
@RequestMapping("/games")
public class GameController {
  
  private GameService gameService;
  private DeckService deckService;

  @GetMapping
  public List<Game> getAll() {
    return gameService.findAll();
  }

  @GetMapping("/{uuid}")
  public Game find(@PathVariable UUID uuid) {
    return gameService.find(uuid);
  }

  @GetMapping("/{uuid}/decks")
  public List<Deck> getDecks(@PathVariable UUID uuid) {
    return deckService.findAllGameDecks(uuid);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Game add() {
    return gameService.create();
  }

  @PostMapping("/{uuid}/decks")
  public Deck addDeck(@PathVariable UUID uuid) {
    return gameService.addDeck(uuid);
  }

  @PostMapping("/{uuid}/deal-cards")
  public List<Card> dealCards(@PathVariable Long playerId, @PathVariable UUID gameUuid) {
    return gameService.dealCard(playerId, gameUuid);
  }

  @PostMapping("/{uuid}")
  @ResponseStatus(HttpStatus.CREATED)
  public Player addPlayer(@PathVariable UUID gameUuid, @PathVariable String nickname) {
    return gameService.addPlayer(gameUuid, nickname);
  }
  
}
