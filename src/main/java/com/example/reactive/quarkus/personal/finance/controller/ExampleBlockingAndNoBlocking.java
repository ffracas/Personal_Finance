package com.example.reactive.quarkus.personal.finance.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/finance")
public class ExampleBlockingAndNoBlocking {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        System.out.println("hello " + Thread.currentThread().getName());
        return Uni.createFrom().item("Hello from Quarkus REST");
    }

    @GET
    @Path("/hello-blocking")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloBlocking() {
        System.out.println("hello " + Thread.currentThread().getName());
        return "hello";
    }

}
