package com.example.javafxexample.json;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONFileWriter extends JSONFileIO {
    public static void write(JSONObject jsonObject) throws IOException {
        setJsonObject(jsonObject);
    }

    public void nonStaticWrite(JSONObject jsonObject) throws IOException {
        setJsonObject(jsonObject);
    }

    private static void setJsonObject(JSONObject jsonObject) throws IOException {
        Path jsonFilePath = Paths.get(JSON_FILE_NAME);
        File jsonFile = new File(String.valueOf(jsonFilePath));
        FileWriter fileWriter = new FileWriter(jsonFile);
        fileWriter.write(jsonObject.toString());
        fileWriter.close();
    }
}
