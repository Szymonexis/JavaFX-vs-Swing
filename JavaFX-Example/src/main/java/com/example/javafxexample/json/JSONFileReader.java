package com.example.javafxexample.json;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JSONFileReader extends JSONFileIO {
    public static JSONObject read() throws IOException {
        return getJsonObject();
    }

    public JSONObject nonStaticRead() throws IOException {
        return getJsonObject();
    }

    private static JSONObject getJsonObject() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(JSON_FILE_NAME));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        return new JSONObject(content);
    }
}
