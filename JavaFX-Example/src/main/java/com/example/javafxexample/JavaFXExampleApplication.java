package com.example.javafxexample;

import com.example.javafxexample.enums.JSONKeysEnum;
import com.example.javafxexample.json.JSONFileWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

public class JavaFXExampleApplication extends Application {

    public static FXMLLoader formLoader;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSONKeysEnum.NAME.getText(), "");
        jsonObject.put(JSONKeysEnum.SURNAME.getText(), "");
        jsonObject.put(JSONKeysEnum.DATE_OF_BIRTH.getText(), "");
        jsonObject.put(JSONKeysEnum.EMAIL.getText(), "");
        jsonObject.put(JSONKeysEnum.PASSWORD.getText(), "");

        JSONFileWriter.write(jsonObject);
    }

    public static void main(String[] args) {
        launch();
    }
}