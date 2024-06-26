package com.example.cardgame.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.example.cardgame.models.Card;
import com.example.cardgame.models.Game;
import com.example.cardgame.models.Player;
import com.example.cardgame.repositories.PlayerRepository;


@Service
@Transactional
public class PlayerService {

	@Autowired
  private PlayerRepository playerRepository;

	public Player create(Game game, String nickname) {
		return playerRepository.save(new Player(game, nickname));
	}

	public Player find(Long id) {
		return playerRepository.findById(id).orElseThrow();
	}	

	public List<Player> findAll(UUID gameUuid) {
		return playerRepository.findAllByGameUuid(gameUuid);
	}

	public List<Card> getCards(Long id) {
		Player player = playerRepository.findById(id).orElseThrow();

		return player.getCards();
	}

}
