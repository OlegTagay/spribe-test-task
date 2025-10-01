package com.spribe.project.utils;

import java.time.Instant;

public class StringUtils {
    private static final String prefix = "atf-";

    public static String generateLogin() {
        StringBuilder sb = new StringBuilder(prefix + "login");
        sb.append("-");
        sb.append(Instant.now());

        return sb.toString();
    }

    public static String generatePassword() {
        StringBuilder sb = new StringBuilder(prefix + "password");
        sb.append("-");
        sb.append(Instant.now());

        return sb.toString();
    }

    public static String generateScreenName() {
        StringBuilder sb = new StringBuilder(prefix + "screen_name");
        sb.append("-");
        sb.append(Instant.now());

        return sb.toString();
    }
}
