package com.example.reactive.quarkus.personal.finance.controller;

import com.example.reactive.quarkus.personal.finance.service.DepositService;
import jakarta.ws.rs.Path;

@Path("/deposit")
public class DepositController {

    private final DepositService depositService;


    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }


}
