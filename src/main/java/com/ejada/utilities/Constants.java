package com.ejada.utilities;

import java.util.ResourceBundle;

public class Constants
{

	private static final ResourceBundle ENVIRONMENT_RN = ResourceBundle.getBundle("environment");
	public static final String ENVIRONMENT_NAME = ENVIRONMENT_RN.getString("env.name");
	public static final String APPLICATION_HOST = ENVIRONMENT_RN.getString("app.host");

	public static String AUTH_TOKEN = "598db7d418f442f30811513b3d6a115d8256a56c4510297760d15f42d7d9104f";

}
