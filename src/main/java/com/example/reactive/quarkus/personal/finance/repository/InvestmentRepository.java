package com.example.reactive.quarkus.personal.finance.repository;

import com.example.reactive.quarkus.personal.finance.model.entity.Investment;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class InvestmentRepository implements PanacheRepositoryBase<Investment, UUID> {
}
