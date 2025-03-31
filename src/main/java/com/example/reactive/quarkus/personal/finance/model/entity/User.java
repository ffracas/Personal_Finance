package com.example.reactive.quarkus.personal.finance.model.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
public class User extends PanacheEntity {

    @Column(length = 100, nullable = false)
    public String name;

    @Column(length = 255, unique = true, nullable = false)
    public String email;

    @Column(length = 255, nullable = false)
    public String passwordHash;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime creationDate = LocalDateTime.now();
}
