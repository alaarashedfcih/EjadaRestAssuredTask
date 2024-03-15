package com.ejada.tests;

import com.ejada.utilities.ConfigReader;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;
import static com.ejada.controller.BaseController.clientRegistrationRequest;
import static com.ejada.utilities.Utilities.extractElementFromResponse;
import static com.ejada.utilities.Constants.AUTH_TOKEN;
public class BaseTest {
    public BaseTest(){

    }

    String requestBody = "{\n" +
            "   \"clientName\": \""+ ConfigReader.getName()+"\",\n" +
            "   \"clientEmail\": \""+ ConfigReader.getEmail()+"\"\n" +
            "}";
    @BeforeSuite(alwaysRun = true)
    public void setupRegistration(){
//        Response registerResponse = clientRegistrationRequest(requestBody);
//        registerResponse.prettyPrint();
//        registerResponse.then().assertThat().statusCode(201);
//        AUTH_TOKEN = (String) extractElementFromResponse(registerResponse , "accessToken");
//        System.out.println(AUTH_TOKEN);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

}
