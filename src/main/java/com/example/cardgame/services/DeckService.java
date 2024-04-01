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
import com.example.cardgame.repositories.DeckRepository;

@Service
@Transactional
public class DeckService {

  @Autowired
  private DeckRepository deckRepository;

  public List<Deck> findAllGameDecks(UUID gameUuid) {
    return deckRepository.findAllDecksByGameUuid(gameUuid);
  }

  public Deck create() {
    Deck deck = new Deck();

    return deckRepository.save(deck);
  }

  public Deck addDeck(UUID gameUuid) {
    Deck deck = create();

    int decksCount = findAllGameDecks(gameUuid).size();

    deck.setCards(createCards(deck, decksCount * 52));

    return deckRepository.save(deck);
  }

  public List<Card> createCards(Deck deck, int decksCount) {    
		List<Card> cards = new ArrayList<Card>();

    int index = 0; 

    do {
      for (Suit suit : Suit.values()) {
        for (Face face : Face.values()) {
          cards.add(new Card(face, suit, deck));
        }

        index++;
      }
    } while (index <= decksCount);
		
		return cards;
	}
}
