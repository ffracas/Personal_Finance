package com.example.reactive.quarkus.personal.finance.model.response;

import com.example.reactive.quarkus.personal.finance.model.entity.User;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DepositResponseDto (Long userId, BigDecimal investedAmount, BigDecimal annualRate,
                                  LocalDate startDate, LocalDate endDate) {
}
