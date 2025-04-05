package com.example.reactive.quarkus.personal.finance.service;

import com.example.reactive.quarkus.personal.finance.converter.TransactionConverter;
import com.example.reactive.quarkus.personal.finance.model.entity.Transaction;
import com.example.reactive.quarkus.personal.finance.model.request.TransactionRequestDto;
import com.example.reactive.quarkus.personal.finance.model.response.TransactionResponseDto;
import com.example.reactive.quarkus.personal.finance.repository.TransactionRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public final class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;

    public TransactionService(TransactionRepository transactionRepository, TransactionConverter transactionConverter) {
        this.transactionRepository = transactionRepository;
        this.transactionConverter = transactionConverter;
    }

    private static Transaction updateTransaction(Transaction transaction, TransactionRequestDto transactionRequestDto) {
        transaction.setTransactionDate(transactionRequestDto.transactionDate());
        transaction.setAmount(transactionRequestDto.amount());
        transaction.setType(transactionRequestDto.type().toLowerCase());
        transaction.setCategory(transactionRequestDto.category());
        transaction.setSubCategory(transactionRequestDto.subCategory());
        return transaction;
    }

    public Uni<TransactionResponseDto> getTransactionById(String transactionId) {
        return transactionRepository.getTransactionById(UUID.fromString(transactionId))
                .map(transactionConverter::toDto);
    }

    public Multi<Set<TransactionResponseDto>> getAllTransaction() {
        return transactionRepository.getAllTransactions()
                .toMulti()
                .map(transactionConverter::toDto);
    }

    public Uni<TransactionResponseDto> createTransaction(TransactionRequestDto transactionRequestDto) {
        return transactionRepository.saveTransaction(transactionConverter.toEntity(transactionRequestDto))
                .map(transactionConverter::toDto);
    }

    @WithTransaction
    public Uni<TransactionResponseDto> updateTransaction(TransactionRequestDto transactionRequestDto, String transactionId) {
        return transactionRepository.findById(UUID.fromString(transactionId))
                .map(transaction -> updateTransaction(transaction, transactionRequestDto))
                .flatMap(transaction -> transactionRepository.persist(transaction)
                        .map(transactionConverter::toDto));
    }

    public Uni<Boolean> deleteTransaction(String transactionId) {
        return transactionRepository.deleteTransaction(UUID.fromString(transactionId));
    }
}
