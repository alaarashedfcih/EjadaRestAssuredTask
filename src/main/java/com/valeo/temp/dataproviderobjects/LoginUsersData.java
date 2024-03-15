package com.valeo.temp.dataproviderobjects;

import com.valeo.temp.utilities.CustomAnnotations;

public class LoginUsersData
{

	@CustomAnnotations.ExcelColumn(1)
	String testCaseName;

	@CustomAnnotations.ExcelColumn(2)
	String UserName;

	@CustomAnnotations.ExcelColumn(3)
	String password;

	public String getTestCaseName()
	{
		return testCaseName;
	}

	public String getUserName()
	{
		return UserName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setTestCaseName(String testCaseName)
	{
		this.testCaseName = testCaseName;
	}

	public void setUserName(String userName)
	{
		UserName = userName;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}

