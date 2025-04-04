package com.example.reactive.quarkus.personal.finance.model.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Investments")
@Getter
@Setter
public class Investment extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @Column(length = 100)
    public String name;

    @Column(length = 50)
    public String code;

    @Column(name = "shares_owned", precision = 15, scale = 4)
    public BigDecimal sharesOwned;

    @Column(name = "unit_price", precision = 15, scale = 4)
    public BigDecimal unitPrice;

    @Column(name = "investment_date", nullable = false)
    public LocalDate investmentDate;

    @Column(name = "current_value", precision = 15, scale = 2)
    public BigDecimal currentValue;
}
