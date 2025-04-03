package com.example.reactive.quarkus.personal.finance.service;

import com.example.reactive.quarkus.personal.finance.converter.TransactionConverter;
import com.example.reactive.quarkus.personal.finance.model.request.TransactionRequestDto;
import com.example.reactive.quarkus.personal.finance.model.response.TransactionResponseDto;
import com.example.reactive.quarkus.personal.finance.repository.TransactionRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;

    public TransactionService(TransactionRepository transactionRepository, TransactionConverter transactionConverter) {
        this.transactionRepository = transactionRepository;
        this.transactionConverter = transactionConverter;
    }

    public Uni<TransactionResponseDto> getTransactionById(long transactionId) {
    }

    public Multi<List<TransactionResponseDto>> getAllTransaction() {
    }

    public Uni<TransactionResponseDto> createTransaction(TransactionRequestDto transactionRequestDto) {
    }

    public Uni<TransactionResponseDto> updateTransaction(TransactionRequestDto transactionRequestDto) {
    }

    public Uni<Void> deleteTransaction(long transactionId) {
    }
}
