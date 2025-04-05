package com.example.reactive.quarkus.personal.finance.service;

import com.example.reactive.quarkus.personal.finance.converter.InvestmentConverter;
import com.example.reactive.quarkus.personal.finance.model.entity.Investment;
import com.example.reactive.quarkus.personal.finance.model.request.InvestmentRequestDto;
import com.example.reactive.quarkus.personal.finance.model.response.InvestmentResponseDto;
import com.example.reactive.quarkus.personal.finance.repository.InvestmentRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public final class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final InvestmentConverter investmentConverter;

    public InvestmentService(InvestmentRepository investmentRepository, InvestmentConverter investmentConverter) {
        this.investmentRepository = investmentRepository;
        this.investmentConverter = investmentConverter;
    }

    private static Investment updateInvestment(Investment investment, InvestmentRequestDto investmentRequestDto) {
        investment.setCode(investmentRequestDto.code());
        investment.setName(investmentRequestDto.name());
        investment.setInvestmentDate(investmentRequestDto.investmentDate());
        investment.setCurrentValue(investmentRequestDto.currentValue());
        investment.setSharesOwned(investmentRequestDto.sharedOwned());
        investment.setUnitPrice(investmentRequestDto.unitPrice());
        return investment;
    }

    public Uni<InvestmentResponseDto> createInvestment(InvestmentRequestDto investmentRequestDto) {
        return investmentRepository.saveInvestment(investmentConverter.toEntity(investmentRequestDto))
                .map(investmentConverter::toDto);
    }

    public Uni<InvestmentResponseDto> getInvestmentById(String investmentId) {
        return investmentRepository.getInvestmentById(UUID.fromString(investmentId))
                .map(investmentConverter::toDto);
    }

    public Multi<Set<InvestmentResponseDto>> getAllInvestments() {
        return investmentRepository.getAllInvestment()
                .map(investmentConverter::toDto);
    }

    public Uni<Boolean> deleteInvestmentById(String investmentId) {
        return investmentRepository.deleteInvestment(UUID.fromString(investmentId));
    }

    public Uni<InvestmentResponseDto> updateInvestment(InvestmentRequestDto investmentRequestDto, String investmentId) {
        return investmentRepository.getInvestmentById(UUID.fromString(investmentId))
                .map(investment -> updateInvestment(investment, investmentRequestDto))
                .flatMap(investment -> investmentRepository.saveInvestment(investment)
                        .map(investmentConverter::toDto));
    }
}
