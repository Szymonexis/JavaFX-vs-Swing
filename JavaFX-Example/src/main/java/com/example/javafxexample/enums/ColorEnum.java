package com.example.javafxexample.enums;

public enum ColorEnum {
    RED("-fx-text-fill: red"),
    BLACK("-fx-text-fill: black");

    private final String value;

    ColorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
