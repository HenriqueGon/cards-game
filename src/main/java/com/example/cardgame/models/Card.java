package com.example.cardgame.models;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
  
  public enum Face {
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
    private int faceValue;

    private Face(int value) {
      this.faceValue = value;
    };
  };
  
  public enum Suit {
    Hearts(1),
    Spades(2),
    Clubs(3),
    Diamonds(4);

    @Getter
    private int cardSuit;

    private Suit(int suit) {
      this.cardSuit = suit; 
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
  @JsonBackReference
  private Deck deck;

  @ManyToOne
  @JoinColumn(name = "player_id")
  @Getter
  @Setter
  @JsonBackReference
  private Player player;

  @Getter
  @Setter
  private Integer index;

  @Enumerated(EnumType.STRING)
  @Getter
  @Setter
  private Face face;

  @Enumerated(EnumType.STRING)
  @Getter
  @Setter
  private Suit suit;

  @Column(columnDefinition = "TIMESTAMP", updatable = false)
  @CreatedDate
  @Getter
  private Instant createdAt;

  @Column(columnDefinition = "TIMESTAMP")
  @LastModifiedDate
  @Getter
  private Instant updatedAt;

  public Card() {}

  public Card(Face face, Suit suit, Deck deck) {
    this.face = face;
    this.suit = suit;
    this.deck = deck;
  }

}
