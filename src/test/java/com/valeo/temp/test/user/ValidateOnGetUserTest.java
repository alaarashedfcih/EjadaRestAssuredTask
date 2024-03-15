package com.valeo.temp.test.user;

import com.valeo.temp.controller.BaseController;
import com.valeo.temp.dataproviderobjects.LoginUsersData;
import com.valeo.temp.utilities.DataProviderSource;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ValidateOnGetUserTest
{

	@BeforeMethod(alwaysRun = true)
	public synchronized void setUp(Method method, Object testData[], ITestContext ctx) throws JSONException
	{

		if (((LoginUsersData) testData[0]).getTestCaseName() != "")
		{
			ctx.setAttribute(method.getName(), ((LoginUsersData) testData[0]).getTestCaseName());
		}
		else
		{
			ctx.setAttribute(method.getName(), "Validate Login with valid credentials");
		}

	}

	@Test(alwaysRun = true, dataProvider = "LoginUsersDataFeed", dataProviderClass = DataProviderSource.class)
	public static void Login_validateOnValidLoginTest(LoginUsersData data) throws JSONException
	{
		JSONObject json = new JSONObject();
		json.put("username", data.getUserName());
		json.put("password", data.getPassword());

		given().relaxedHTTPSValidation().spec(BaseController.createRequestSpecificationForGetUser()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(json.toString()).log().all().get().then().log().all().assertThat()
				.statusCode(200).and().assertThat().body("data.first_name", equalTo("Janet")).and().assertThat()
				.body("data.last_name", equalTo("Weaver"));

	}

}
