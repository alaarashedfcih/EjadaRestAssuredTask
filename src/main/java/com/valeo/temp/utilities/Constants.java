package com.valeo.temp.utilities;

import java.util.ResourceBundle;

public class Constants
{

	private static final ResourceBundle ENVIRONMENT_RN = ResourceBundle.getBundle("environment");
	public static final String ENVIRONMENT_NAME = ENVIRONMENT_RN.getString("env.name");
	public static final String APPLICATION_HOST = ENVIRONMENT_RN.getString("app.host");
	public static final String MAIL_CONFIG_FLAG = ENVIRONMENT_RN.getString("email.properties.flag");
	public static final String RUN_ON_REMOTE_SERVER_FLAG = ENVIRONMENT_RN.getString("run.on.remote.server.flag");

	/***********Data Provider Excel***********/
	public static final String LOGIN_LOGOUT_USERS_WORKBOOK = "LoginLogoutUsersData.xlsx";
	public static final String LOGIN_USERS_SHEET = "LoginService";

}
