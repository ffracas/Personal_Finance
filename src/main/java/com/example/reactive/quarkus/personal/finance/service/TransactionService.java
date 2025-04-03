package com.example.reactive.quarkus.personal.finance.service;

import com.example.reactive.quarkus.personal.finance.converter.TransactionConverter;
import com.example.reactive.quarkus.personal.finance.model.request.TransactionRequestDto;
import com.example.reactive.quarkus.personal.finance.model.response.TransactionResponseDto;
import com.example.reactive.quarkus.personal.finance.repository.TransactionRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;

@ApplicationScoped
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;

    public TransactionService(TransactionRepository transactionRepository, TransactionConverter transactionConverter) {
        this.transactionRepository = transactionRepository;
        this.transactionConverter = transactionConverter;
    }

    public Uni<TransactionResponseDto> getTransactionById(long transactionId) {
        return transactionRepository.getTransactionById(transactionId)
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

    public Uni<TransactionResponseDto> updateTransaction(TransactionRequestDto transactionRequestDto) {
        return transactionRepository.saveTransaction(transactionConverter.toEntity(transactionRequestDto))
                .map(transactionConverter::toDto);
    }

    public Uni<Void> deleteTransaction(long transactionId) {
        return transactionRepository.deleteTransaction(transactionId);
    }
}
