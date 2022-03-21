package com.example.javafxexample.controllers;

import com.example.javafxexample.enums.JSONKeysEnum;
import com.example.javafxexample.enums.StepEnum;
import com.example.javafxexample.json.JSONFileReader;
import com.example.javafxexample.json.JSONFileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.JSONObject;

import java.io.IOException;

public class StepOneController extends ChildController {
    private JSONObject jsonObject;
    private Boolean isName = false;
    private Boolean isSurname = false;

    public Label nameLabel;
    public Label surnameLabel;
    public Label birthLabel;

    public TextField nameTextField;
    public TextField surnameTextField;
    public Button nextButton;
    public DatePicker birthDatePicker;

    @FXML
    public void initialize() throws IOException {
        jsonObject = JSONFileReader.read();
        nameTextField.setText((String) jsonObject.get(JSONKeysEnum.NAME.getText()));
        surnameTextField.setText((String) jsonObject.get(JSONKeysEnum.SURNAME.getText()));
    }

    public void onInput(KeyEvent keyEvent) {
        if(nameTextField.getText().isEmpty()) {
            nameLabel.setStyle("-fx-text-fill: red");
            isName = false;
        } else {
            nameLabel.setStyle("-fx-text-fill: black");
            isName = true;
        }
        if(surnameTextField.getText().isEmpty()) {
            surnameLabel.setStyle("-fx-text-fill: red");
            isSurname = false;
        } else {
            surnameLabel.setStyle("-fx-text-fill: black");
            isSurname = true;
        }
        nextButton.setDisable(!(isName && isSurname));
    }

    public void onNext(ActionEvent actionEvent) throws IOException {
        if (isName && isSurname) {
            parentController.childOnChangeForm(StepEnum.TWO);
            jsonObject = JSONFileReader.read();
            jsonObject.put(JSONKeysEnum.NAME.getText(), nameTextField.getText());
            jsonObject.put(JSONKeysEnum.SURNAME.getText(), surnameTextField.getText());
            jsonObject.put(JSONKeysEnum.DATE_OF_BIRTH.getText(), birthDatePicker.getValue());
            JSONFileWriter.write(jsonObject);
        }
    }

}
