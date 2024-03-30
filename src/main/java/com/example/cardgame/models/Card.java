package com.example.cardgame.models;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Card {
  
  public enum CardFace {
    A(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    J(11),
    Q(12),
    K(13);

    @Getter
    private int face_value;

    CardFace(int value) {
      this.face_value = value;
    };
  };
  
  public enum CardSuit {
    Hearts(1),
    Spades(2),
    Clubs(3),
    Diamonds(4);

    @Getter
    private int card_suit;

    CardSuit(int suit) {
      this.card_suit = suit; 
    }
  };

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private long id;

  @ManyToOne
  @JoinColumn(name = "deck_id")
  @Getter
  @Setter
  private Deck deck;

  @Enumerated(EnumType.STRING)
  @Getter
  private CardFace cardFace;

  @Enumerated(EnumType.STRING)
  @Getter
  private CardSuit suit;

  @Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
  @CreatedDate
  @Getter
  private Instant created_at;

  @Column(columnDefinition = "TIMESTAMP", nullable = false)
  @LastModifiedDate
  @Getter
  private Instant updated_at;

}