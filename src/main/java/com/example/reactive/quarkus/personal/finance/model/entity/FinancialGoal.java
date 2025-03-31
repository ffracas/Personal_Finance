package com.example.reactive.quarkus.personal.finance.model.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "FinancialGoals")
public class FinancialGoal extends PanacheEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    public User user;

    @Column(precision = 15, scale = 2)
    public BigDecimal emergencyFundTarget;

    @Column(precision = 15, scale = 2)
    public BigDecimal investmentTarget;

    @Column(precision = 15, scale = 2)
    public BigDecimal bondsTarget;

    @Column(precision = 15, scale = 2)
    public BigDecimal loansTarget;

    @Column(precision = 5, scale = 2)
    public BigDecimal investmentAllocation;

    @Column(precision = 5, scale = 2)
    public BigDecimal currentExpensesAllocation;

    @Column(precision = 5, scale = 2)
    public BigDecimal lifestyleExpensesAllocation;

    public Integer emergencyMonths;
}
