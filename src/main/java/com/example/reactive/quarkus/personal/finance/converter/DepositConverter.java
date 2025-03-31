package com.example.reactive.quarkus.personal.finance.converter;

import com.example.reactive.quarkus.personal.finance.model.entity.DepositAccount;
import com.example.reactive.quarkus.personal.finance.model.request.DepositRequestDto;
import com.example.reactive.quarkus.personal.finance.model.response.DepositResponseDto;

public class DepositConverter implements Converter<DepositRequestDto, DepositResponseDto, DepositAccount> {
    @Override
    public DepositResponseDto toDto(DepositAccount entity) {
        return new DepositResponseDto(entity.user.id, entity.investedAmount, entity.annualRate,
                entity.startDate, entity.endDate);
    }

    @Override
    public DepositAccount toEntity(DepositRequestDto dto) {
        DepositAccount deposit = new DepositAccount();
        // todo: deposit.user da vedere
        deposit.annualRate = dto.annualRate();
        deposit.investedAmount = dto.investedAmount();
        deposit.startDate = dto.startDate();
        deposit.endDate = dto.endDate();
        return deposit;
    }

    @Override
    public DepositAccount toEntity(Long id, DepositRequestDto dto) {
        DepositAccount deposit = toEntity(dto);
        deposit.id = id;
        return deposit;
    }
}
