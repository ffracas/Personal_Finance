package com.example.reactive.quarkus.personal.finance.repository;

import com.example.reactive.quarkus.personal.finance.model.entity.DepositAccount;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;

public class DepositRepository implements PanacheRepository<DepositAccount> {
}
