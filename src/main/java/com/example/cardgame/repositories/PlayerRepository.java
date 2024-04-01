package com.example.cardgame.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cardgame.models.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

  public List<Player> findAllByGameUuid(UUID gameUuid);
}
