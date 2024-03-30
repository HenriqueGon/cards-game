package com.example.cardgame.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.cardgame.models.Player;
import com.example.cardgame.repositories.PlayerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlayerService {

  private PlayerRepository playerRepository;

  public void deletePlayer(UUID gameUuid) {
		Player player = playerRepository.findOneByGameUuid(gameUuid).orElseThrow();
    
		playerRepository.delete(player);
	}

}
