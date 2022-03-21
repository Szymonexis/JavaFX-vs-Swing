package com.example.javafxexample.controllers;

import com.example.javafxexample.enums.StepEnum;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class StepZeroController extends ChildController {

    public Button startButton;

    public void onStart(ActionEvent actionEvent) throws IOException {
        parentController.childOnChangeForm(StepEnum.ONE);
    }
}
