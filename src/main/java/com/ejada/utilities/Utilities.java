package com.ejada.utilities;

import io.restassured.response.Response;

public class Utilities
{
    public static Object extractElementFromResponse(Response response, String ElementPath){
        return response.then() .extract().jsonPath().getString(ElementPath);
    }
}
