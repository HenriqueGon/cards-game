package com.example.cardgame.models;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Long id;

  @UuidGenerator
  private String uuid;

  @OneToMany(mappedBy = "game")
  @Getter
  @Setter
  private List<Deck> decks;

  @Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
  @CreatedDate
  @Getter
  private Instant created_at;

  @Column(columnDefinition = "TIMESTAMP", nullable = false)
  @LastModifiedDate
  @Getter
  private Instant updated_at;

}
