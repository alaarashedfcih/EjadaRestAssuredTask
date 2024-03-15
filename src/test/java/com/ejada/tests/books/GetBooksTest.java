package com.ejada.tests.books;

import com.ejada.tests.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.ejada.controller.BooksController.getBooksRequest;
import static org.hamcrest.Matchers.hasItems;

public class GetBooksTest extends BaseTest {
    @Test(alwaysRun = true)
    public void books_GetBooksTest(){
        Response  getBooksResponse = getBooksRequest();
        getBooksResponse.then().assertThat()
                .statusCode(200)
                .body("id", hasItems(1, 2, 3, 4, 5, 6))
                .body("name", hasItems("The Russian", "Just as I Am", "The Vanishing Half", "The Midnight Library", "Untamed", "Viscount Who Loved Me"))
                .body("type", hasItems("fiction", "non-fiction"))
                .body("available", hasItems(true, false));
    }
}
