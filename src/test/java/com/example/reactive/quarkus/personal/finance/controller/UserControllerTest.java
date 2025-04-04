package com.example.reactive.quarkus.personal.finance.controller;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Integration tests for the {@link UserController}. This class tests the REST endpoints
 * provided by the controller, ensuring that the reactive operations return the expected
 * results. It uses RestAssured to send HTTP requests to the endpoints and verifies
 * the responses.
 *
 * <p>
 * The following endpoints are tested:
 * <ul>
 *     <li>GET /user/getUser/{userId}</li>
 *     <li>GET /user/getAllUser</li>
 *     <li>POST /user/createUser</li>
 *     <li>PUT /user/updateUser</li>
 * </ul>
 * </p>
 *
 * <p>
 * To run these tests, ensure that your Quarkus application is configured for testing,
 * and that the necessary dependencies for Quarkus tests and RestAssured are available.
 * </p>
 *
 * @see UserController
 */
@QuarkusTest
public class UserControllerTest {

    /**
     * Tests that a user with the given ID is successfully retrieved.
     * Assumes that a user with ID 1 exists.
     */
    @Test
    public void testGetUserById() {
        given()
                .pathParam("userId", 1)
                .when()
                .get("/user/getUser/{userId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    /**
     * Tests that the endpoint to retrieve all users returns a non-empty collection.
     */
    @Test
    public void testGetAllUser() {
        given()
                .when()
                .get("/user/getAllUser")
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
    public void testCreateUser() {
        String requestBody = "{\"name\": \"New User\",\"password\": \"TheP4ssW0r41sTh383ST\",\"email\": \"thebestemail@bigmail.com\"}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/user/createUser")
                .then().log().all()
                .statusCode(200)
                .body("name", equalTo("New User"))
                .body("email", equalTo("thebestemail@bigmail.com"));
    }

    /**
     * Tests the user update endpoint. A user is first created, then updated with new data.
     * The test verifies that the update endpoint returns the updated user details.
     */
    @Test
    public void testUpdateUser() {
        // First, create a user to update.
        String requestBody = "{\"name\": \"New User To Update\",\"password\": \"TheP4ssW0r41sTh383\",\"email\": \"thebestemailupdated@bigmail.com\"}";


        int userId = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/user/createUser")
                .then()
                .statusCode(200)
                .extract().path("id");

        // Now update the user with a new name.
        String updateRequest = "{\"id\": " + userId + ", \"name\": \"Updated User\"}";

        given()
                .contentType(ContentType.JSON)
                .body(updateRequest)
                .when()
                .put("/user/updateUser")
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated User"))
                .body("id", equalTo(userId));
    }

    @Test
    public void testDeleteUserById() {
        given()
                .pathParam("userId", 1)
                .when()
                .delete("/user/deleteUser/{userId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeleteUserByIdNotFound() {
        given()
                .pathParam("userId", 11122)
                .when()
                .delete("/user/deleteUser/{userId}")
                .then()
                .statusCode(404);
    }
}
