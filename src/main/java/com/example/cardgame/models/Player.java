package com.example.cardgame.models;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Player {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "game_id")
  @Getter
  @Setter
  private Game game;

  @OneToMany(mappedBy = "player")
  @Getter
  @Setter
  private List<Deck> decks;

  @Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
  @CreatedDate
  @Getter
  private Instant createdAt;

  @Column(columnDefinition = "TIMESTAMP", nullable = false)
  @LastModifiedDate
  @Getter
  private Instant updatedAt;

  public Player() {}

  public Player(Game game) {
    this.game = game;
  }
    
}
