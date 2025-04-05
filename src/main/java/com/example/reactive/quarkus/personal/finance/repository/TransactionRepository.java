package com.example.reactive.quarkus.personal.finance.repository;

import com.example.reactive.quarkus.personal.finance.model.entity.Transaction;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TransactionRepository implements PanacheRepositoryBase<Transaction, UUID> {
    @WithTransaction
    public Uni<Transaction> getTransactionById(UUID id) {
        return findById(id);
    }

    @WithTransaction
    public Uni<List<Transaction>> getAllTransactions() {
        return listAll();
    }

    @WithTransaction
    public Uni<Transaction> saveTransaction(Transaction transaction) {
        return persist(transaction)
                .onFailure()
                .invoke(Log::error);
    }

    @WithTransaction
    public Uni<Boolean> deleteTransaction(UUID transaction) {
        return deleteById(transaction);
    }
}
