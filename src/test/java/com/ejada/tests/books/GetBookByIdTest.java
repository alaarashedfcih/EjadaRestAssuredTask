package com.ejada.tests.books;

import com.ejada.tests.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.ejada.controller.BooksController.getBookByIdRequest;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GetBookByIdTest extends BaseTest {

    private static final int EXPECTED_ID = 4;
    private static final String EXPECTED_NAME = "The Midnight Library";
    private static final String EXPECTED_AUTHOR = "Matt Haig";
    private static final String EXPECTED_TYPE = "fiction";
    private static final float EXPECTED_PRICE = 15.6f; // Convert to float
    private static final int EXPECTED_STOCK = 87;
    private static final boolean EXPECTED_AVAILABLE = true;

    @Test(alwaysRun = true)
    public void books_GetBookByIdTest(){
        Response getBookByIdResponse = getBookByIdRequest(4);
        getBookByIdResponse.then().assertThat()
                .statusCode(200)
                .body("id", equalTo(EXPECTED_ID))
                .body("name", equalTo(EXPECTED_NAME))
                .body("author", equalTo(EXPECTED_AUTHOR))
                .body("type", equalTo(EXPECTED_TYPE))
                .body("price", equalTo(EXPECTED_PRICE))
                .body("current-stock", equalTo(EXPECTED_STOCK))
                .body("available", equalTo(EXPECTED_AVAILABLE));
    }
}
