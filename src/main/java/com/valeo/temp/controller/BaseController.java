package com.valeo.temp.controller;

import com.valeo.temp.utilities.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

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

}
