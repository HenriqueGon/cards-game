package com.example.cardgame.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardgame.models.Deck;

import com.example.cardgame.services.DeckService;

@CrossOrigin
@RestController
@RequestMapping("/decks")
public class DeckController {
  
  private DeckService deckService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Deck add() {
    return deckService.create();
  }
}
