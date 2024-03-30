package com.example.cardgame.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cardgame.models.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long>{
  
	Deck findOneByGameUuid(UUID uuid);
}
