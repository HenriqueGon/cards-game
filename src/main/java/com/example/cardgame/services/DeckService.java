package com.example.cardgame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

  private DeckRepository deckRepository;

  public List<Deck> findAllGameDecks(UUID gameUuid) {
    return deckRepository.findAllDecksByGameUuid(gameUuid);
  }

  public Deck create() {
    Deck deck = new Deck();

    deck.setCards(createCards(deck));

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

  public void shuffleCards() {
    
  }

}
