package com.ejada.tests.books;

import com.ejada.tests.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.ejada.controller.BooksController.getStatusRequest;
import static org.hamcrest.Matchers.equalTo;

public class GetStatusTest {
    @Test(alwaysRun = true)
    public void books_GetStatusTest(){
        Response getStatusResponse = getStatusRequest();
        getStatusResponse.then().assertThat().statusCode(200).body("status", equalTo("OK"));
    }
}
