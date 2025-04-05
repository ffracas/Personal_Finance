package com.example.reactive.quarkus.personal.finance.repository;

import com.example.reactive.quarkus.personal.finance.model.entity.Investment;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InvestmentRepository implements PanacheRepositoryBase<Investment, UUID> {
    @WithTransaction
    public Uni<Investment> saveInvestment(Investment investment) {
        return persist(investment)
                .onFailure()
                .invoke(Log::error);
    }

    @WithTransaction
    public Multi<List<Investment>> getAllInvestment() {
        return listAll().toMulti();
    }

    @WithTransaction
    public Uni<Boolean> deleteInvestment(UUID investmentId) {
        return deleteById(investmentId);
    }

    @WithTransaction
    public Uni<Investment> getInvestmentById(UUID investmentId) {
        return findById(investmentId);
    }
}
