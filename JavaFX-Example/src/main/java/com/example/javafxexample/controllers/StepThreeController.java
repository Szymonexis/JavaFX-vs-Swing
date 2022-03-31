package com.example.javafxexample.controllers;

import com.example.javafxexample.enums.JSONKeysEnum;
import com.example.javafxexample.json.JSONFileReader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;

public class StepThreeController extends ChildController {
    private JSONObject jsonObject;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String email;
    private String password;

    public Text endText;
    public Label endLabel;
    public Button revealButton;

    public StepThreeController() throws IOException {
        getJsonData();
    }

    @FXML
    public void initialize()  {
        setEndText();
    }

    private void getJsonData() throws IOException {
        jsonObject = JSONFileReader.read();
        name = (String) jsonObject.get(JSONKeysEnum.NAME.getText());
        surname = (String) jsonObject.get(JSONKeysEnum.SURNAME.getText());
        dateOfBirth = (String) jsonObject.get(JSONKeysEnum.DATE_OF_BIRTH.getText());
        email = (String) jsonObject.get(JSONKeysEnum.EMAIL.getText());
        password = (String) jsonObject.get(JSONKeysEnum.PASSWORD.getText());
    }

    private void setEndText() {
        if (name.isEmpty() || name.isBlank() || surname.isEmpty() || surname.isBlank()
                || dateOfBirth.isEmpty() || dateOfBirth.isBlank() || password.isEmpty() || password.isBlank()
                || email.isEmpty() || email.isBlank()) {
            endLabel.setText("Sorry, I couldn't load all of the data\nPlease try again :3");
        } else {
            String endTextString = "";
            endTextString += "Hello " + name + " " + surname + " :)" + "\n";
            endTextString += "You were born " + dateOfBirth + "\n";
            endTextString += "Your e-mail address is: " + email + "\n";
            endTextString += "Your super secret password is: " + password;

            endLabel.setText(endTextString);
        }
    }

    public void onReveal() throws IOException {
        getJsonData();
        setEndText();
        revealButton.setDisable(true);
        revealButton.setVisible(false);
        endLabel.setVisible(true);
    }
}