package com.example.javafxexample.enums;

public enum StyleClassEnum {
    WRONG("-fx-text-fill: orange; -fx-background-color: rgba(255, 255, 255, 0.20); " +
            "-fx-background-radius: 12em; -fx-padding: 0.5em;"),
    GOOD("-fx-text-fill: white;");

    private final String value;

    StyleClassEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
