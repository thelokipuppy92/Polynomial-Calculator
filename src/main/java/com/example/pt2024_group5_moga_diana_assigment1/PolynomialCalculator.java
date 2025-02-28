package com.example.pt2024_group5_moga_diana_assigment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PolynomialCalculator {

    @FXML
    private Button resetButton;

    @FXML
    private Button SubstractButton;

    @FXML
    private Button addButton;

    @FXML
    private Button multiplyButton;

    @FXML
    private TextField polynomial1Field;

    @FXML
    private TextField polynomial2Field;

    @FXML
    private Label resultLabel;


    @FXML
    public static void changeScene() throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PolynomialCalculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // scene

        // Set the userID in the new controller
        PolynomialCalculator controller = fxmlLoader.getController();


        Stage stage = HelloApplication.getPrimaryStage();
        stage.hide();
        stage.setTitle("Polynomial Calculator");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void addButtonAction(ActionEvent event) {
        String polynomial1 = polynomial1Field.getText();
        String polynomial2 = polynomial2Field.getText();

        Polynomial poly1 = Polynomial.parsePolynomial(polynomial1);
        Polynomial poly2 = Polynomial.parsePolynomial(polynomial2);

        Polynomial result = poly1.add(poly2);
        resultLabel.setText(result.toString());
    }

    @FXML
    private void subtractButtonAction(ActionEvent event) {
        String polynomial1 = polynomial1Field.getText();
        String polynomial2 = polynomial2Field.getText();

        Polynomial poly1 = Polynomial.parsePolynomial(polynomial1);
        Polynomial poly2 = Polynomial.parsePolynomial(polynomial2);

        Polynomial result = poly1.subtract(poly2);
        resultLabel.setText(result.toString());
    }

    @FXML
    private void multiplyButtonAction(ActionEvent event) {
        String polynomial1 = polynomial1Field.getText();
        String polynomial2 = polynomial2Field.getText();

        Polynomial poly1 = Polynomial.parsePolynomial(polynomial1);
        Polynomial poly2 = Polynomial.parsePolynomial(polynomial2);

        Polynomial result = poly1.multiply(poly2);
        resultLabel.setText(result.toString());
    }

    @FXML
    private void derivativeButtonAction(ActionEvent event) {
        String polynomial1 = polynomial1Field.getText();
        //String polynomial2 = polynomial2Field.getText();

        Polynomial poly1 = Polynomial.parsePolynomial(polynomial1);
        //Polynomial poly2 = Polynomial.parsePolynomial(polynomial2);

        Polynomial result = poly1.derivative();
        resultLabel.setText(result.toString());
    }

    @FXML
    private void integrationButtonAction(ActionEvent event) {
        String polynomial1 = polynomial1Field.getText();
        //String polynomial2 = polynomial2Field.getText();

        Polynomial poly1 = Polynomial.parsePolynomial(polynomial1);
        //Polynomial poly2 = Polynomial.parsePolynomial(polynomial2);

        Polynomial result = poly1.integrate();
        resultLabel.setText(result.toString());
    }

    @FXML
    private void resetButtonAction(ActionEvent event) {
        polynomial1Field.clear();
        polynomial2Field.clear();
        resultLabel.setText("");
    }


}
