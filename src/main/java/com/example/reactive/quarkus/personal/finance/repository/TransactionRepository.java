package com.example.reactive.quarkus.personal.finance.repository;

import com.example.reactive.quarkus.personal.finance.model.entity.Transaction;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {
    @WithTransaction
    public Uni<Transaction> getTransactionById(long id) {
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
    public Uni<Void> deleteTransaction(long transaction) {
        return deleteById(transaction)
                .onFailure()
                .invoke(Log::error)
                .replaceWithVoid();
    }
}
