package com.example.reactive.quarkus.personal.finance.model.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "RecurringExpenses")
@Getter
@Setter
public class RecurringExpense extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @Column(precision = 15, scale = 2, nullable = false)
    public BigDecimal amount;

    @Column(length = 100)
    public String category;

    @Column(length = 20)
    public String frequency;

    @Column(name = "start_date", nullable = false)
    public LocalDate startDate;

    @Column(name = "end_date")
    public LocalDate endDate;
}
