package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
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
    private Stage stage;
    private Scene scene;
    private Parent root;

    private static String login="root";
    private static String password="";
    private static String URL="jdbc:mysql://localhost:3306/mikroprojekt3";

    private Connection connection = DriverManager.getConnection(URL,login,password);

    public HelloController() throws SQLException {
    }

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Pracownik pracownik = DatabaseConnection.login(loginText.getText(), passwordText.getText(),connection);
        if(pracownik!=null){
            errorLabel.setText("");
            if(pracownik.isAdmin()){
                switchAdmin(pracownik);
            }else{
                switchUser(pracownik);
            }
        }
        else{
            errorLabel.setText("Niepoprawne dane dostepowe");
        }
    }


    void switchUser(Pracownik pracownik) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panelUser.fxml"));
        root=loader.load();
        UserController userController = loader.getController();
        //userController.init(connection);
        stage = (Stage)loginText.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    void switchAdmin(Pracownik pracownik) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panelAdmin.fxml"));
        root=loader.load();
        AdminController adminController = loader.getController();
        adminController.init(connection);
        stage = (Stage)loginText.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}