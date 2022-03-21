package com.example.javafxexample.enums;

public enum JSONKeysEnum {
    NAME("name"),
    SURNAME("surname"),
    EMAIL("email"),
    DATE_OF_BIRTH("dateOfBirth"),
    PASSWORD("password");

    private final String text;

    JSONKeysEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
