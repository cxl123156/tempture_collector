package com.cxl.iot.temperature;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final String PROPERTIES_FILE = "config.properties";

    public static String getPropertiesConfig(String key) throws IOException {
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties.getProperty(key);
    }

}
