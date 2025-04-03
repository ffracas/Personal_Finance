package com.example.reactive.quarkus.personal.finance.controller;

import com.example.reactive.quarkus.personal.finance.model.request.TransactionRequestDto;
import com.example.reactive.quarkus.personal.finance.model.response.TransactionResponseDto;
import com.example.reactive.quarkus.personal.finance.service.TransactionService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;

import java.util.Set;

@Path("/transaction")
public final class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GET
    @Path("/getTransactionById/{transactionId}")
    public Uni<TransactionResponseDto> getTransactionById(@PathParam("transactionId") long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @GET
    @Path("/getAllTransaction")
    public Multi<Set<TransactionResponseDto>> getAllTransaction() {
        return transactionService.getAllTransaction();
    }

    @POST
    @Path("/createTransaction")
    public Uni<TransactionResponseDto> createTransaction(TransactionRequestDto transactionRequestDto) {
        return transactionService.createTransaction(transactionRequestDto);
    }

    @PUT
    @Path("/updateTransaction")
    public Uni<TransactionResponseDto> updateTransaction(TransactionRequestDto transactionRequestDto) {
        return transactionService.updateTransaction(transactionRequestDto);
    }

    @DELETE
    @Path("/deleteTransaction")
    public Uni<Void> deleteTransaction(long transactionId) {
        return transactionService.deleteTransaction(transactionId);
    }
}
