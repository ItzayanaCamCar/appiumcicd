package com.qa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static final Properties properties = new Properties();
    TestUtils utils = new TestUtils();

    public Properties getProperties() throws IOException {
        InputStream inputStream = null;
        String propsFileName = "config.properties";

        if (properties.isEmpty()) {
            try {
                utils.log().info("Loading config properties");
                inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Failed to load config properties. " + e.toString());
                throw e;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
        return properties;
    }

}
