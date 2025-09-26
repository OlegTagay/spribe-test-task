package com.spribe.project.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties props = new Properties();

    static {
        load("config/framework.properties");
        String env = System.getProperty("env", "dev");
        load(String.format("config/application-%s.properties", env));
    }

    private static void load(String fileName) {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + fileName, e);
        }
    }

    public static String get(String key, String defaultValue) {
        return System.getProperty(key, props.getProperty(key, defaultValue));
    }

    public static int getInt(String key, int defaultValue) {
        return Integer.parseInt(get(key, String.valueOf(defaultValue)));
    }

    public static String getBaseUri() {
        return get("base.uri", "http://localhost:8080");
    }

    public static int getThreadCount() {
        return getInt("thread.count", Runtime.getRuntime().availableProcessors());
    }

    public static int getTimeout() {
        return getInt("timeout", 30);
    }

    public static int getRetryCount() {
        return getInt("retry.count", 0);
    }
}
