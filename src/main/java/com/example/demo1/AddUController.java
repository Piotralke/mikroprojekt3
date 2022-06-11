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
import java.util.List;

public class AddUController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection connection;
    @FXML
    private TextField loginText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;
    @FXML
    private Label doneLabel;
    @FXML
    private Label errorLabel;

    public void init(Connection connection2){
        connection=connection2;
    }

    @FXML
    protected void addUser(){
        if(DatabaseConnection.addWorker(connection,loginText.getText(), passwordText.getText(), nameText.getText(), surnameText.getText())){
            doneLabel.setVisible(true);
            errorLabel.setVisible(false);
        }else{
            doneLabel.setVisible(false);
            errorLabel.setVisible(true);
        }

    }
    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panelAdmin.fxml"));
        root=loader.load();
        AdminController adminController = loader.getController();
        adminController.init(connection);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
