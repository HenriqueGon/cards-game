package com.example.cardgame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cardgame.models.Card;

public interface CardRepository extends JpaRepository<Card, Long>{
  
}
