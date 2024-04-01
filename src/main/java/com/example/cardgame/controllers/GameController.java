package com.example.cardgame.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardgame.models.Card;
import com.example.cardgame.models.Deck;
import com.example.cardgame.models.Game;
import com.example.cardgame.models.Player;
import com.example.cardgame.services.DeckService;
import com.example.cardgame.services.GameService;
import com.example.cardgame.services.PlayerService;

@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {
  
  @Autowired
  private GameService gameService;
  @Autowired
  private DeckService deckService;
  @Autowired
  private PlayerService playerService;

  @GetMapping
  public List<Game> getAll() {
    return gameService.findAll();
  }

  @GetMapping("/{uuid}")
  public Game find(@PathVariable UUID uuid) {
    return gameService.find(uuid);
  }

  @GetMapping("/{uuid}/players")
  public List<Player> findAllPlayers(@PathVariable UUID uuid) {
    return playerService.findAll(uuid);
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
  public void addDeck(@PathVariable UUID uuid) {
    gameService.addDeck(uuid);
  }

  @PostMapping("/{uuid}/deal-cards")
  @ResponseBody
  public List<Card> dealCards(@RequestParam Long playerId, @PathVariable UUID uuid) {
    return gameService.dealCard(playerId, uuid);
  }

  @PostMapping("/{uuid}/shuffle")
  public void shuffleCards(@PathVariable UUID uuid) {
    gameService.shuffle(uuid);
  }

  @PostMapping("/{uuid}/players")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Player addPlayer(@PathVariable UUID uuid, @RequestParam String nickname) {
    return gameService.addPlayer(uuid, nickname);
  }

  @DeleteMapping("/{uuid}/players/{id}")
  @ResponseBody
  public void removePlayer(@PathVariable UUID uuid, @RequestParam Long playerId) {
    gameService.deletePlayer(uuid, playerId);
  }
  
}
