package com.valeo.temp.controller;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BooksController extends BaseController{
    public static Response getStatusRequest () {
        return given()
                .relaxedHTTPSValidation()
                .spec(baseRequestSpecification)
                .when().get();

    }
}
