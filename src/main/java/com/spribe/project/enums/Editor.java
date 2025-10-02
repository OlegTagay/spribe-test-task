package com.spribe.project.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Editor {
    SUPERVISOR("supervisor"),
    INVALID("invalid"),
    ADMIN("admin");

    private final String value;

    Editor(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Editor fromValue(String value) {
        for (Editor editor : Editor.values()) {
            if (editor.value.equalsIgnoreCase(value)) {
                return editor;
            }
        }
        throw new IllegalArgumentException("Unknown editor: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
