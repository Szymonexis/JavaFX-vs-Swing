package com.example.javafxexample.controllers;

public abstract class ChildController {
    protected MainWindowController parentController;

    public void setParentController(MainWindowController parentController) {
        this.parentController = parentController;
    }
}
