package com.example.javafxexample.controllers;

import com.example.javafxexample.JavaFXExampleApplication;
import com.example.javafxexample.enums.StepEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MainWindowController {
    private StepEnum currentRadial = null;
    private ChildController childController = null;

    public BorderPane formBorderPane;
    public Label stepLabel;
    public ToggleGroup menuToggleGroup;
    public RadioButton menuStepOneRadioButton;
    public RadioButton menuStepTwoRadioButton;
    public RadioButton menuStepThreeRadioButton;

    @FXML
    public void initialize() throws IOException {
        stepLabel.setText("Click on the 'Start' button :3");
        JavaFXExampleApplication.formLoader = new FXMLLoader(getClass().getResource("stepZero.fxml"));
        GridPane gridPane = JavaFXExampleApplication.formLoader.load();
        formBorderPane.setCenter(gridPane);
        currentRadial = StepEnum.ZERO;
        childController = JavaFXExampleApplication.formLoader.getController();
        childController.setParentController(this);
    }

    public void childOnChangeForm(StepEnum stepEnum) throws IOException {
        changeForm(stepEnum);
    }

    private void changeForm(StepEnum stepEnum) throws IOException {
        if (currentRadial != stepEnum) {
            stepLabel.setText(stepEnum.getText());
            JavaFXExampleApplication.formLoader = new FXMLLoader(getClass().getResource(stepEnum.getFileName()));
            GridPane gridPane = JavaFXExampleApplication.formLoader.load();
            formBorderPane.setCenter(gridPane);
            childController = JavaFXExampleApplication.formLoader.getController();
            childController.setParentController(this);
            currentRadial = stepEnum;
        }
        setCurrentRadial(stepEnum);
    }

    private void setCurrentRadial(StepEnum stepEnum) {
        switch (stepEnum) {
            case ZERO -> menuToggleGroup.selectToggle(null);
            case ONE -> menuToggleGroup.selectToggle(menuStepOneRadioButton);
            case TWO -> menuToggleGroup.selectToggle(menuStepTwoRadioButton);
            case THREE -> menuToggleGroup.selectToggle(menuStepThreeRadioButton);
        }
    }

    public void onChangeForm(ActionEvent actionEvent) throws IOException {
        RadioButton currentToggle = (RadioButton) menuToggleGroup.getSelectedToggle();

        if (currentToggle.equals(menuStepOneRadioButton)) {
            changeForm(StepEnum.ONE);
        } else if (currentToggle.equals(menuStepTwoRadioButton)) {
            changeForm(StepEnum.TWO);
        } else if (currentToggle.equals(menuStepThreeRadioButton)) {
            changeForm(StepEnum.THREE);
        }
    }
}