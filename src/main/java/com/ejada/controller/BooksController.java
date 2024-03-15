package com.ejada.controller;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.ejada.utilities.Constants.AUTH_TOKEN;
import static io.restassured.RestAssured.given;

public class BooksController extends BaseController{
    public static Response getStatusRequest () {
        return given()
                .relaxedHTTPSValidation()
                .spec(baseRequestSpecification)
                .when().get("/status");
    }

    public static Response getBooksRequest () {
        return given()
                .relaxedHTTPSValidation()
                .spec(baseRequestSpecification)
                .when().get("/books");
    }

    public static Response getBookByIdRequest (int bookId) {
        return given()
                .pathParam("bookId",bookId)
                .relaxedHTTPSValidation()
                .spec(baseRequestSpecification)
                .when().get("/books/{bookId}");
    }

    public static Response submitOrderRequest (String requestBody ) {
        return given()
                .headers(
                        "Authorization",
                        "Bearer " + AUTH_TOKEN)
                .relaxedHTTPSValidation()
                .spec(baseRequestSpecification)
                .with().body(requestBody)
                .when().post("/orders");
    }

    public static Response getOrdersRequest () {
        return given()
                .headers(
                        "Authorization",
                        "Bearer " + AUTH_TOKEN)
                .relaxedHTTPSValidation()
                .spec(baseRequestSpecification)
                .when().get("/orders");
    }

    public static Response updateOrderRequest (String requestBody, String orderId) {
        return given()
                .pathParam("orderId",orderId)
                .headers(
                        "Authorization",
                        "Bearer " + AUTH_TOKEN)
                .relaxedHTTPSValidation()
                .spec(baseRequestSpecification)
                .with().body(requestBody)
                .when().patch("/orders/{orderId}");
    }

    public static Response deleteOrderRequest (String orderId) {
        return given()
                .pathParam("orderId",orderId)
                .headers(
                        "Authorization",
                        "Bearer " + AUTH_TOKEN)
                .relaxedHTTPSValidation()
                .spec(baseRequestSpecification)
                .when().delete("/orders/{orderId}");
    }
}
