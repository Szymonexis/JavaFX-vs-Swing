package com.example.javafxexample.controllers;

import com.example.javafxexample.enums.StyleClassEnum;
import com.example.javafxexample.enums.InputTypeEnum;
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
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;

public class StepOneController extends ChildController {
    private JSONObject jsonObject;
    private Boolean isName = false;
    private Boolean isSurname = false;
    private Boolean isBirth = false;

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
        String date = (String) jsonObject.get(JSONKeysEnum.DATE_OF_BIRTH.getText());
        String name = (String) jsonObject.get(JSONKeysEnum.NAME.getText());
        String surname = (String) jsonObject.get(JSONKeysEnum.SURNAME.getText());

        if (!name.equals("") && !surname.equals("") && !date.equals("")) {
            nextButton.setDisable(false);
            isBirth = true;
            isSurname = true;
            isName = true;
        }

        nameTextField.setText(name);
        surnameTextField.setText(surname);
        if (!date.equals("")) {
            birthDatePicker.setValue(LocalDate.parse(date));
        }
    }

    public void onInputName() {
        onInput(InputTypeEnum.NAME);
    }

    public void onInputSurname() {
        onInput(InputTypeEnum.SURNAME);
    }

    public void onInputBirth() {
        onInput(InputTypeEnum.BIRTH);
    }

    private void onInput(InputTypeEnum inputType) {
        switch (inputType) {
            case NAME -> {
                if(nameTextField.getText().isEmpty()) {
                    nameLabel.setStyle(StyleClassEnum.WRONG.getValue());
                    isName = false;
                } else {
                    nameLabel.setStyle(StyleClassEnum.GOOD.getValue());
                    isName = true;
                }
            }
            case SURNAME -> {
                if(surnameTextField.getText().isEmpty()) {
                    surnameLabel.setStyle(StyleClassEnum.WRONG.getValue());
                    isSurname = false;
                } else {
                    surnameLabel.setStyle(StyleClassEnum.GOOD.getValue());
                    isSurname = true;
                }
            }
            case BIRTH -> {
                if(birthDatePicker.getValue() == null) {
                    birthLabel.setStyle(StyleClassEnum.WRONG.getValue());
                    isBirth = false;
                } else {
                    birthLabel.setStyle(StyleClassEnum.GOOD.getValue());
                    isBirth = true;
                }
            }
        }
        nextButton.setDisable(!(isName && isSurname && isBirth));
    }

    public void onNext(ActionEvent actionEvent) throws IOException {
        if (isName && isSurname && isBirth) {
            parentController.childOnChangeForm(StepEnum.TWO);
            jsonObject = JSONFileReader.read();
            jsonObject.put(JSONKeysEnum.NAME.getText(), nameTextField.getText());
            jsonObject.put(JSONKeysEnum.SURNAME.getText(), surnameTextField.getText());
            jsonObject.put(JSONKeysEnum.DATE_OF_BIRTH.getText(), birthDatePicker.getValue());
            JSONFileWriter.write(jsonObject);
        }
    }

}
