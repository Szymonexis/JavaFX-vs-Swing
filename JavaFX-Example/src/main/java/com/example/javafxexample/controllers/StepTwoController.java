package com.example.javafxexample.controllers;

import com.example.javafxexample.enums.StyleClassEnum;
import com.example.javafxexample.enums.InputTypeEnum;
import com.example.javafxexample.enums.JSONKeysEnum;
import com.example.javafxexample.enums.StepEnum;
import com.example.javafxexample.json.JSONFileReader;
import com.example.javafxexample.json.JSONFileWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Pattern;

public class StepTwoController extends ChildController {
    private JSONObject jsonObject;
    private Boolean isPassword = false;
    private Boolean isConfirmPassword = false;
    private Boolean isEmail = false;
    private final Pattern VALID_EMAIL_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    public Label emailLabel;
    public Label passwordLabel;
    public Label confirmPasswordLabel;

    public TextField emailTextField;
    public Button submitButton;
    public PasswordField passwordField;
    public PasswordField confirmPasswordField;

    @FXML
    public void initialize() throws IOException {
        jsonObject = JSONFileReader.read();
        String email = (String) jsonObject.get(JSONKeysEnum.EMAIL.getText());
        String password = (String) jsonObject.get(JSONKeysEnum.PASSWORD.getText());

        isEmail = !email.equals("");
        isPassword = !password.equals("");

        emailTextField.setText(email);
        passwordField.setText(password);
        confirmPasswordField.setText("");
    }

    public void onInputEmail() {
        onInput(InputTypeEnum.EMAIL);
    }

    public void onInputPassword() {
        onInput(InputTypeEnum.PASSWORD);
    }

    public void onInputConfirmPassword() {
        onInput(InputTypeEnum.CONFIRM_PASSWORD);
    }

    private void onInput(InputTypeEnum inputType) {
        switch (inputType) {
            case EMAIL -> {
                isEmail = VALID_EMAIL_REGEX.matcher(emailTextField.getText()).find();
                if(!isEmail) {
                    emailLabel.setStyle(StyleClassEnum.WRONG.getValue());
                } else {
                    emailLabel.setStyle(StyleClassEnum.GOOD.getValue());
                }
            }
            case PASSWORD -> {
                isPassword = !passwordField.getText().isEmpty()
                        && passwordField.getText().length() >= 8;
                if(!isPassword) {
                    passwordLabel.setStyle(StyleClassEnum.WRONG.getValue());
                } else {
                    passwordLabel.setStyle(StyleClassEnum.GOOD.getValue());
                }
            }
            case CONFIRM_PASSWORD -> {
                isConfirmPassword = passwordField.getText().equals(confirmPasswordField.getText());
                if(!isConfirmPassword) {
                    confirmPasswordLabel.setStyle(StyleClassEnum.WRONG.getValue());
                } else {
                    confirmPasswordLabel.setStyle(StyleClassEnum.GOOD.getValue());
                }
            }
        }
        submitButton.setDisable(!(isEmail && isPassword && isConfirmPassword));
    }

    public void onSubmit() throws IOException {
        if (isEmail && isPassword && isConfirmPassword) {
            parentController.childOnChangeForm(StepEnum.THREE);
            jsonObject = JSONFileReader.read();
            jsonObject.put(JSONKeysEnum.EMAIL.getText(), emailTextField.getText());
            jsonObject.put(JSONKeysEnum.PASSWORD.getText(), passwordField.getText());
            JSONFileWriter.write(jsonObject);
        }
    }
}
