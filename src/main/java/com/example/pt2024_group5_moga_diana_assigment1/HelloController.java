package com.example.pt2024_group5_moga_diana_assigment1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {


    @FXML
    public static void changeScene() throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // scene

        // Set the userID in the new controller
        HelloController controller = fxmlLoader.getController();


        Stage stage = HelloApplication.getPrimaryStage();
        stage.hide();
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void change() throws IOException {
        PolynomialCalculator.changeScene();
    }

}