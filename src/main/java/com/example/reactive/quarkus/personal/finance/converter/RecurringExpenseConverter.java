package com.example.reactive.quarkus.personal.finance.converter;

import com.example.reactive.quarkus.personal.finance.model.entity.RecurringExpense;
import com.example.reactive.quarkus.personal.finance.model.request.RecurringExpenseRequestDto;
import com.example.reactive.quarkus.personal.finance.model.response.RecurringExpenseResponseDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecurringExpenseConverter implements Converter<RecurringExpenseRequestDto, RecurringExpenseResponseDto, RecurringExpense> {
    @Override
    public RecurringExpenseResponseDto toDto(RecurringExpense entity) {
        return null;
    }

    @Override
    public RecurringExpense toEntity(RecurringExpenseRequestDto dto) {
        return null;
    }

    @Override
    public RecurringExpense toEntity(Long id, RecurringExpenseRequestDto dto) {
        return null;
    }
}
