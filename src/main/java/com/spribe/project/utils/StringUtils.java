package com.spribe.project.utils;

import java.time.Instant;

public class StringUtils {
    private static final String prefix = "atf-";

    public static String generateLogin() {

        return prefix + "login" + "-" +
                Instant.now();
    }

    public static String generateLogin(String text) {

        return prefix + text + "-" + "login" + "-" +
                Instant.now();
    }

    public static String generatePassword() {
        return prefix + "password" + "-" +
                Instant.now();
    }

    public static String generateScreenName() {

        return prefix + "screen_name" + "-" +
                Instant.now();
    }
}
