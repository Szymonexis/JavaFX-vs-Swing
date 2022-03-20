package com.example.exampleswing1;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class ExampleSwing1Application {
    public static void main(String[] args) {
        //creating instance of JFrame
        JFrame jFrame = new JFrame();

        //creating instance of JButton
        JButton jButton = new JButton("click");
        jButton.setBounds(150, 150, 100, 50);

        String text = "Hello there :3";
        JLabel jLabel = new JLabel(text);
        jLabel.setBounds(150, 200, 100, 50);

        //adding button in JFrame
        jFrame.add(jButton);
        jFrame.add(jLabel);

        //400 width and 500 height
        jFrame.setSize(400, 400);
        //using no layout managers
        jFrame.setLayout(null);
        //making the frame visible
        jFrame.setVisible(true);
    }
}

