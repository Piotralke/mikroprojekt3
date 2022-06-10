package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private TextField loginText;
    @FXML
    private TextField passwordText;
    @FXML
    private Label errorLabel;

    private static String login="root";
    private static String password="";
    private static String URL="jdbc:mysql://localhost:3306/mikroprojekt3";

    private Connection connection = DriverManager.getConnection(URL,login,password);

    public HelloController() throws SQLException {
    }

    @FXML
    protected void onHelloButtonClick() throws SQLException {
        Pracownik pracownik = DatabaseConnection.login(loginText.getText(), passwordText.getText(),connection);
        if(pracownik!=null){
            errorLabel.setText("");
            if(pracownik.isAdmin()){

            }

        }
        else{
            errorLabel.setText("Niepoprawne dane dostepowe");
        }
    }
}