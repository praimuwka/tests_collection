package ru.praimuwka.hawkingbros.tools;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {
    RUSSIAN("ru"),
    ENGLISH("en"),
    SPANISH("es");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    @JsonValue
    String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
