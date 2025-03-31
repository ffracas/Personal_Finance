package com.example.reactive.quarkus.personal.finance.service;

import com.example.reactive.quarkus.personal.finance.model.response.FinanceResponseDto;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.util.List;

@ApplicationScoped
public class FinanceService {
    private final List<FinanceResponseDto> financeResponseDtos = List.of(new FinanceResponseDto("beer", 493.4),
            new FinanceResponseDto("beer", 493.4), new FinanceResponseDto("beer", 493.4),
            new FinanceResponseDto("beer", 493.4), new FinanceResponseDto("beer", 493.4),
            new FinanceResponseDto("beer", 49.4), new FinanceResponseDto("beer", 493.4),
            new FinanceResponseDto("beer", 4913.4), new FinanceResponseDto("beer", 493.4),
            new FinanceResponseDto("beer", 44393.4), new FinanceResponseDto("beer", 493.4),
            new FinanceResponseDto("beer", 493.4), new FinanceResponseDto("beer", 493.4),
            new FinanceResponseDto("beer", 3.4), new FinanceResponseDto("beer", 493.4),
            new FinanceResponseDto("beer", 93.42), new FinanceResponseDto("beer", 493.4),
            new FinanceResponseDto("beer", 93.3), new FinanceResponseDto("beer", 493.4),
            new FinanceResponseDto("beer", 93.4));

    public Multi<FinanceResponseDto> getFinanceResponseDtos() {
        Multi<Long> ticks = Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onOverflow().drop();
        return Multi.createBy().combining().streams(ticks, Multi.createFrom().items(financeResponseDtos.stream()))
                .using((tick, item) -> item);
    }
}
