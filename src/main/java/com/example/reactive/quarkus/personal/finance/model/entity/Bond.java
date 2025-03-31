package com.example.reactive.quarkus.personal.finance.model.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Bonds")
public class Bond extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @Column(length = 100)
    public String name;

    @Column(length = 50)
    public String code;

    @Column(precision = 15, scale = 2, nullable = false)
    public BigDecimal investedAmount;

    @Column(precision = 5, scale = 2)
    public BigDecimal annualRate;

    @Column(nullable = false)
    public LocalDate maturityDate;

    @Column(length = 50)
    public String couponType;

    @Column(precision = 15, scale = 2)
    public BigDecimal currentValue;
}
