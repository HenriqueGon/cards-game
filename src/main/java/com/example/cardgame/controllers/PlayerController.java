package com.example.cardgame.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardgame.models.Card;
import com.example.cardgame.models.Player;
import com.example.cardgame.services.PlayerService;

@CrossOrigin
@RestController
@RequestMapping("/players")
public class PlayerController {

  private PlayerService playerService;

  @GetMapping("/players/{id}")
  public Player find(@PathVariable Long id) {
    return playerService.find(id);
  }

  @GetMapping("/players/{id}/cards")
  public List<Card> getCards(@PathVariable Long id) {
    return playerService.getCards(id);
  }

}
