package com.example.reactive.quarkus.personal.finance.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/transaction")
public final class TransactionController {
    //TODO REMOVE WHEN CREATE OTHER ENDPOINT
    @GET
    public Uni<String> dummyEndpoint() {
        return Uni.createFrom().item("transaction");
    }
}
