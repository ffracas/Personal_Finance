package com.example.reactive.quarkus.personal.finance.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DepositRequestDto (Long userId, BigDecimal investedAmount, BigDecimal annualRate,
                                  LocalDate startDate, LocalDate endDate) {
}