package com.spribe.project.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties props = new Properties();
    private static final Logger log = LoggerFactory.getLogger(ConfigManager.class);

    static {
        load("config/framework.properties");
        String env = get("framework.active.env", "dev");
        load(String.format("config/application-%s.properties", env));

        log.info("Active profile: " + getActiveProfile());
        log.debug("Using config settings:\n" + props);
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
        return get("application.base.uri", "http://localhost:8080");
    }

    public static String getActiveProfile() {
        return get("framework.active.profile", "dev");
    }

    public static int getThreadCount() {
        return getInt("framework.thread.count", Runtime.getRuntime().availableProcessors());
    }

    public static int getTimeout() {
        return getInt("framework.http.timeout", 30);
    }

    public static int getRetryCount() {
        return getInt("framework.retry.count", 0);
    }
}
