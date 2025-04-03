package com.example.reactive.quarkus.personal.finance.controller;

import com.example.reactive.quarkus.personal.finance.model.request.TransactionRequestDto;
import com.example.reactive.quarkus.personal.finance.model.response.TransactionResponseDto;
import com.example.reactive.quarkus.personal.finance.service.TransactionService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Set;

@Path("/transaction")
@Tag(name = "Transaction Operations", description = "Endpoints for managing transactions")
public final class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GET
    @Path("/getTransactionById/{transactionId}")
    @Operation(summary = "Retrieve a transaction by its ID", description = "Fetches the details of a specific transaction using its unique identifier.")
    @APIResponse(responseCode = "200", description = "Transaction retrieved successfully", content = @Content(schema = @Schema(implementation = TransactionResponseDto.class)))
    @APIResponse(responseCode = "404", description = "Transaction not found")
    public Uni<TransactionResponseDto> getTransactionById(@PathParam("transactionId") long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @GET
    @Path("/getAllTransaction")
    @Operation(summary = "Retrieve all transactions", description = "Fetches a list of all transactions.")
    @APIResponse(responseCode = "200", description = "List of transactions retrieved successfully", content = @Content(schema = @Schema(implementation = TransactionResponseDto.class)))
    public Multi<Set<TransactionResponseDto>> getAllTransaction() {
        return transactionService.getAllTransaction();
    }

    @POST
    @Path("/createTransaction")
    @Operation(summary = "Create a new transaction", description = "Submits a new transaction to be processed.")
    @APIResponse(responseCode = "201", description = "Transaction created successfully", content = @Content(schema = @Schema(implementation = TransactionResponseDto.class)))
    public Uni<TransactionResponseDto> createTransaction(TransactionRequestDto transactionRequestDto) {
        return transactionService.createTransaction(transactionRequestDto);
    }

    @PUT
    @Path("/updateTransaction")
    @Operation(summary = "Update an existing transaction", description = "Modifies details of an existing transaction.")
    @APIResponse(responseCode = "200", description = "Transaction updated successfully", content = @Content(schema = @Schema(implementation = TransactionResponseDto.class)))
    @APIResponse(responseCode = "404", description = "Transaction not found")
    public Uni<TransactionResponseDto> updateTransaction(TransactionRequestDto transactionRequestDto) {
        return transactionService.updateTransaction(transactionRequestDto);
    }

    @DELETE
    @Path("/deleteTransaction")
    @Operation(summary = "Delete a transaction", description = "Removes a transaction from the system using its ID.")
    @APIResponse(responseCode = "204", description = "Transaction deleted successfully")
    @APIResponse(responseCode = "404", description = "Transaction not found")
    public Uni<Void> deleteTransaction(long transactionId) {
        return transactionService.deleteTransaction(transactionId);
    }
}
