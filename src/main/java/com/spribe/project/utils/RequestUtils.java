package com.spribe.project.utils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class RequestUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> toMap(Object obj) {
        return mapper.convertValue(obj, Map.class);
    }
}
