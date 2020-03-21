package com.techstack.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesContext {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(PropertiesContext.class.getClassLoader().getResourceAsStream("environment.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
