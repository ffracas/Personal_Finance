package com.example.reactive.quarkus.personal.finance.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponseDto(long userId, BigDecimal amount, String category, String subCategory,
                                     String type, LocalDate transactionDate) {
}