package com.ejada.controller;

import com.ejada.utilities.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseController
{

	public static final RequestSpecification baseRequestSpecification = baseRequestSpecificationBuilder();
	public static RequestSpecification baseRequestSpecificationBuilder()
	{

		return new RequestSpecBuilder()
				.setBaseUri(Constants.APPLICATION_HOST)
				.addHeader("Content-Type", "application/json")
				.build();
	}

	public static Response clientRegistrationRequest(String requestBody) {
		return given()
				.relaxedHTTPSValidation()
				.spec(baseRequestSpecification)
				.body(requestBody)
				.when().post("/api-clients/");
	}

}
