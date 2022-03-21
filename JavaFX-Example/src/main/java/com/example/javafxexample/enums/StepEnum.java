package com.example.javafxexample.enums;

public enum StepEnum {
    ZERO("Step 0", "stepZero.fxml"),
    ONE("Step 1", "stepOne.fxml"),
    TWO("Step 2", "stepTwo.fxml"),
    THREE("Step 3", "stepThree.fxml");

    private final String text;
    private final String fileName;

    StepEnum(String text, String fileName) {
        this.text = text;
        this.fileName = fileName;
    }

    public String getText() {
        return text;
    }

    public String getFileName() {
        return fileName;
    }
}
