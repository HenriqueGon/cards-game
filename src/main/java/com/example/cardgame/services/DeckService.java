package com.example.cardgame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.cardgame.models.Card;
import com.example.cardgame.models.Card.Face;
import com.example.cardgame.models.Card.Suit;
import com.example.cardgame.models.Deck;
import com.example.cardgame.models.Game;
import com.example.cardgame.repositories.DeckRepository;

@Service
@Transactional
public class DeckService {

  @Autowired
  private DeckRepository deckRepository;

  public List<Deck> findAllGameDecks(UUID gameUuid) {
    List<Deck> deck = deckRepository.findAllDecksByGameUuid(gameUuid);

    return deck;
  }

  public Deck addDeck(Game game) {
    Deck deck = new Deck();

    List<Card> cards = createCards(deck);

    deck.setGame(game);
    deck.setCards(cards);

    return deckRepository.save(deck);
  }

  public List<Card> createCards(Deck deck) {    
		List<Card> cards = new ArrayList<Card>();

    for (Suit suit : Suit.values()) {
      for (Face face : Face.values()) {
        cards.add(new Card(face, suit, deck));
      }
    }
		
		return cards;
	}
}
