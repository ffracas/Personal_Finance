package com.example.reactive.quarkus.personal.finance.service;

import com.example.reactive.quarkus.personal.finance.converter.DepositConverter;
import com.example.reactive.quarkus.personal.finance.repository.DepositRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepositService {

    private final DepositRepository depositRepo;
    private final DepositConverter converter;

    public DepositService(DepositRepository depositRepo, DepositConverter converter) {
        this.depositRepo = depositRepo;
        this.converter = converter;
    }

}
