package com.ephirious.util;

import com.ephirious.exception.properties.PropertiesFileException;
import com.ephirious.exception.properties.PropertyNotFoundException;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class AppProperties {
    private static final String PROPERTIES_PATH = "properties/application.properties";

    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        ClassLoader loader = AppProperties.class.getClassLoader();

        try (InputStream propertiesInput = loader.getResourceAsStream(PROPERTIES_PATH)) {
            checkPropertiesStream(propertiesInput);
            properties.load(propertiesInput);
        } catch (IOException e) {
            throw new PropertiesFileException("Failed to load properties file: ", e);
        }
    }

    public static String get(String key) {
        String property = properties.getProperty(key);
        checkProperty(key, property);
        return property;
    }

    private static void checkPropertiesStream(InputStream propertiesStream) {
        if (propertiesStream == null) {
            throw new PropertiesFileException("The properties file hasn't found by path: " + PROPERTIES_PATH);
        }
    }

    private static void checkProperty(String key, String property) {
        if (property == null) {
            throw new PropertyNotFoundException("The property " + key + " hasn't found");
        }
    }
}
