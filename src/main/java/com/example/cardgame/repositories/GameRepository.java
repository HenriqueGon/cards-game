package com.example.cardgame.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cardgame.models.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
  
  public Optional<Game> findOneByUuid(UUID uuid);
}
