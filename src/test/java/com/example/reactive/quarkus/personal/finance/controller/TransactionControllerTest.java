package com.example.reactive.quarkus.personal.finance.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
public class TransactionControllerTest {
    @Test
    public void testGetTransactionById() {
        given()
                .pathParam("transactionId", 1)
                .when()
                .get("/transaction/getTransactionById/{transactionId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    /**
     * Tests that the endpoint to retrieve all users returns a non-empty collection.
     */
    @Test
    public void testGetAllTransaction() {
        given()
                .when()
                .get("/transaction/getAllTransaction")
                .then()
                .statusCode(200)
                // Assumes that at least one user exists in the system.
                .body("size()", greaterThanOrEqualTo(1));
    }

    /**
     * Tests the user creation endpoint. A new user is created with the given request data,
     * and the response is verified to contain the correct name and a generated ID.
     */
    @Test
    public void testCreateTransaction() {
        String requestBody = """
                {
                  "userId" : 12345,
                  "amount" : 250.75,
                  "category" : "Utilities",
                  "subCategory" : "Electricity",
                  "type" : "Expense",
                  "transactionDate" : "2025-04-03"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/transaction/createTransaction")
                .then().log().all()
                .statusCode(201)
                .body("amount", equalTo(250.75))
                .body("category", equalTo("Utilities"));
    }

    @Test
    public void testUpdateTransaction() {
        // First, create a user to update.
        String requestBody = """
                {
                  "userId" : 12345,
                  "amount" : 250.75,
                  "category" : "Utilities",
                  "subCategory" : "Electricity",
                  "type" : "Expense",
                  "transactionDate" : "2025-04-03"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/transaction/createTransaction")
                .then()
                .statusCode(201);

        // Now update the user with a new name.
        String updateRequest = """
                {
                  "userId" : 12345,
                  "amount" : 250.75,
                  "category" : "Alco",
                  "subCategory" : "Beer",
                  "type" : "Expense",
                  "transactionDate" : "2025-04-03"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(updateRequest)
                .when()
                .put("/transaction/updateTransaction/{id}")
                .then()
                .statusCode(200)
                .body("category", equalTo("Alco"))
                .body("subCategory", equalTo("Beer"));
    }
}
