package org.example.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class Environment {
    private static final String ENVIRONMENT_SETTINGS = "environment.properties";
    private static final Logger logger = LoggerFactory.getLogger(Environment.class);
    private static Environment environment = null;
    private static Properties properties = null;

    private static Environment getEnvironment() {
        if (environment == null) {
            environment = new Environment();
        }
        return environment;
    }
    public static Environment INSTANCE = getEnvironment();

    public Environment() {
        logger.info("Loading properties from: {}", ENVIRONMENT_SETTINGS);
        properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/" + ENVIRONMENT_SETTINGS));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPropertyByExactKey(String key) {
        return properties.getProperty(key);
    }

}
