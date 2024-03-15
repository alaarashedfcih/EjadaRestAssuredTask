package com.ejada.tests.books;

import com.ejada.tests.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.ejada.utilities.Utilities.extractElementFromResponse;
import static org.hamcrest.Matchers.equalTo;
import static com.ejada.controller.BooksController.submitOrderRequest;
import static org.hamcrest.Matchers.notNullValue;

public class SubmitOrderTest extends BaseTest {
    @Test(alwaysRun = true)
    public void books_SubmitOrderTest(){
        String requestBody = """
                {
                  "bookId": 1,
                  "customerName": "Aly"
                }""";
        Response submitOrderResponse = submitOrderRequest(requestBody);
        submitOrderResponse.then().assertThat()
                .statusCode(201)
                .body("created", equalTo(true))
                .body("orderId", notNullValue());
    }
}
