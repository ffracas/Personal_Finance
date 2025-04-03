package com.example.reactive.quarkus.personal.finance.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RecurringExpenseControllerTest {
    @Test
    void dummyTest() {
        given().get("/recurring-expense").then().statusCode(200);
    }
}
