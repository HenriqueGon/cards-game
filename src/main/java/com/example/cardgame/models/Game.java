package com.example.cardgame.models;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Long id;

  @Getter
  @Column(unique = true, nullable = false)
  private UUID uuid;

  @OneToMany(mappedBy = "game")
  @Getter
  @Setter
  @JsonManagedReference
  private List<Player> players;

  @OneToMany(mappedBy = "game")
  @Getter
  @Setter
  @JsonManagedReference
  private List<Deck> decks;

  @Column(columnDefinition = "TIMESTAMP", updatable = false)
  @CreatedDate
  @Getter
  private Instant createdAt;

  @Column(columnDefinition = "TIMESTAMP")
  @LastModifiedDate
  @Getter
  private Instant updatedAt;

  public Game() {}

  @PrePersist
  public void generateUuid() {
    this.uuid = UUID.randomUUID();
  }

}
