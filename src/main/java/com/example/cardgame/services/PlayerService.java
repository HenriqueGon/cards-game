package com.example.cardgame.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.example.cardgame.models.Player;
import com.example.cardgame.repositories.PlayerRepository;


@Service
@Transactional
public class PlayerService {

  private PlayerRepository playerRepository;

	public Player find(Long id) {
		return playerRepository.findById(id).orElseThrow();
	}

}
