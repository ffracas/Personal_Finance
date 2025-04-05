package com.example.reactive.quarkus.personal.finance.controller;

import com.example.reactive.quarkus.personal.finance.model.request.InvestmentRequestDto;
import com.example.reactive.quarkus.personal.finance.model.response.InvestmentResponseDto;
import com.example.reactive.quarkus.personal.finance.service.InvestmentService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@Path("/investment")
public final class InvestmentController {
    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GET
    @Path("/getAllInvestment")
    public Multi<Set<InvestmentResponseDto>> getAllInvestment() {
        return investmentService.getAllInvestments();
    }

    @GET
    @Path("/getInvestmentById/{investmentId}")
    public Uni<Response> getInvestmentById(@PathParam("investmentId") String investmentId) {
        return investmentService.getInvestmentById(investmentId)
                .map(investmentResponseDto -> Response.ok().entity(investmentResponseDto).build())
                .onFailure()
                .recoverWithItem(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("/createInvestment")
    public Uni<Response> createInvestment(InvestmentRequestDto investmentRequestDto) {
        return investmentService.createInvestment(investmentRequestDto)
                .map(investmentResponseDto -> Response.status(Response.Status.CREATED).entity(investmentResponseDto).build())
                .onFailure()
                .recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
    }

    @PUT
    @Path("/updateInvestment/{investmentId}")
    public Uni<Response> updateInvestment(@PathParam("investmentId") String investmentId, InvestmentRequestDto investmentRequestDto) {
        return investmentService.updateInvestment(investmentRequestDto, investmentId)
                .map(user -> Response.ok(user).status(Response.Status.OK).build())
                .onFailure()
                .recoverWithItem(throwable -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/deleteInvestment/{investmentId}")
    public Uni<Response> deleteInvestment(@PathParam("investmentId") String investmentId) {
        return investmentService.deleteInvestmentById(investmentId)
                .map(result -> result ? Response.status(Response.Status.NO_CONTENT).build() : Response.status(Response.Status.NOT_FOUND).build())
                .onFailure()
                .recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
    }
}
