package com.ejada.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream("config.properties");
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getName() {
        return properties.getProperty("name");
    }

    public static String getEmail() {
        return properties.getProperty("email");
    }
}
