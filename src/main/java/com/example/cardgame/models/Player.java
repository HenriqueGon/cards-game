package com.example.cardgame.models;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
  
  @OneToMany(mappedBy = "player")
  @Getter
  @JsonManagedReference
  private List<Card> cards;

  @ManyToOne
  @JoinColumn(name = "game_id")
  @Getter
  @Setter
  @JsonBackReference
  private Game game;

  @Getter
  @Setter
  private String nickname;

  @Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
  @CreatedDate
  @Getter
  private Instant createdAt;

  @Column(columnDefinition = "TIMESTAMP", nullable = false)
  @LastModifiedDate
  @Getter
  private Instant updatedAt;

  public Player() {}

  public Player(Game game, String nickname) {
    this.game = game;
    this.nickname = nickname;
  }
    
}
