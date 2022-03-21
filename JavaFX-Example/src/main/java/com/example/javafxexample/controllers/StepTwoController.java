package com.example.javafxexample.controllers;

import com.example.javafxexample.enums.JSONKeysEnum;
import com.example.javafxexample.enums.StepEnum;
import com.example.javafxexample.json.JSONFileReader;
import com.example.javafxexample.json.JSONFileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Pattern;

public class StepTwoController extends ChildController {
    private JSONObject jsonObject;
    private Boolean isPassword = false;
    private Boolean isEmail = false;
    private final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

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
        emailTextField.setText((String) jsonObject.get(JSONKeysEnum.EMAIL.getText()));
        passwordField.setText((String) jsonObject.get(JSONKeysEnum.PASSWORD.getText()));
        confirmPasswordField.setText((String) jsonObject.get(JSONKeysEnum.EMAIL.getText()));
        checkPasswords();
        checkEmail();
    }

    public void onInputPassword(KeyEvent keyEvent) {
        checkPasswords();
    }

    private void checkPasswords() {
        isPassword = !passwordField.getText().isEmpty()
                && !confirmPasswordField.getText().isEmpty()
                && passwordField.getText().length() >= 8
                && passwordField.getText().equals(confirmPasswordField.getText());

        if(!isPassword) {
            passwordLabel.setStyle("-fx-text-fill: red");
            confirmPasswordLabel.setStyle("-fx-text-fill: red");
        } else {
            passwordLabel.setStyle("-fx-text-fill: black");
            confirmPasswordLabel.setStyle("-fx-text-fill: black");
        }

        submitButton.setDisable(!(isPassword && isEmail));
    }

    public void onInputEmail(KeyEvent keyEvent) {
        checkEmail();
    }

    private void checkEmail() {
        isEmail = VALID_EMAIL_REGEX.matcher(emailTextField.getText()).find();

        if(!isEmail) {
            emailLabel.setStyle("-fx-text-fill: red");
        } else {
            emailLabel.setStyle("-fx-text-fill: black");
        }

        submitButton.setDisable(!(isPassword && isEmail));
    }

    public void onSubmit(ActionEvent actionEvent) throws IOException {
        if (isPassword && isEmail) {
            parentController.childOnChangeForm(StepEnum.THREE);
            jsonObject = JSONFileReader.read();
            jsonObject.put(JSONKeysEnum.EMAIL.getText(), emailTextField.getText());
            jsonObject.put(JSONKeysEnum.PASSWORD.getText(), passwordField.getText());
            JSONFileWriter.write(jsonObject);
        }
    }
}
