package com.ejada.tests.books;

import com.ejada.tests.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.ejada.controller.BooksController.submitOrderRequest;
import static com.ejada.controller.BooksController.updateOrderRequest;
import static com.ejada.utilities.Utilities.extractElementFromResponse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateOrderTest extends BaseTest {
    String orderID = "";
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        String requestBody = """
                {
                  "bookId": 1,
                  "customerName": "john"
                }""";
        Response submitOrderResponse = submitOrderRequest(requestBody);
        submitOrderResponse.prettyPrint();
        submitOrderResponse.then().assertThat()
                .statusCode(201)
                .body("created", equalTo(true))
                .body("orderId", notNullValue());
        orderID = (String) extractElementFromResponse(submitOrderResponse,"orderId");
    }
    @Test(alwaysRun = true)
    public void books_UpdateOrderTest(){
        String requestBody = """
                {
                  "customerName": "Alaa"
                }""";
        Response updateOrderResponse = updateOrderRequest(requestBody,orderID);
        updateOrderResponse.prettyPrint();
        updateOrderResponse.then().assertThat().statusCode(204);

    }
}
