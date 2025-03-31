package com.example.reactive.quarkus.personal.finance.model.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "DepositAccounts")
public class DepositAccount extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @Column(precision = 15, scale = 2, nullable = false)
    public BigDecimal investedAmount;

    @Column(precision = 5, scale = 2)
    public BigDecimal annualRate;

    @Column(nullable = false)
    public LocalDate startDate;

    @Column(nullable = false)
    public LocalDate endDate;
}
