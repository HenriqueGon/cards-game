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
public class Deck {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Long id;

  @ManyToOne
  @JoinColumn(name = "player_id")
  @Getter
  @Setter
  private Player player;

  @OneToMany(mappedBy = "deck")
  @Getter
  private List<Card> cards;

  @Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
  @CreatedDate
  @Getter
  private Instant createdAt;

  @Column(columnDefinition = "TIMESTAMP", nullable = false)
  @LastModifiedDate
  @Getter
  private Instant updatedAt;

  public void setCards(List<Card> cards) {
    if (this.cards != null && this.cards.size() > 0)
      throw new Error("changing cards in a deck is not allowed");
    this.cards = cards;
  }
  
}
