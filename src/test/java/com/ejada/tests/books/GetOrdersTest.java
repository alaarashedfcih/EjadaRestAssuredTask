package com.ejada.tests.books;

import com.ejada.tests.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.ejada.controller.BooksController.getOrdersRequest;
import static org.hamcrest.Matchers.*;

public class GetOrdersTest  extends BaseTest {
    @Test(alwaysRun = true)
    public void books_GetOrdersTest(){
        Response getOrdersResponse = getOrdersRequest();
        getOrdersResponse.then().assertThat()
                .statusCode(200)
                .body("[0].id", notNullValue())
                .body("[0].bookId", notNullValue())
                .body("[0].customerName",notNullValue())
                .body("[0].createdBy", notNullValue())
                .body("[0].quantity", equalTo(1))
                .body("[0].timestamp", greaterThan(0L));


    }
}
