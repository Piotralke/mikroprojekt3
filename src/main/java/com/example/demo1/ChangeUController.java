package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class ChangeUController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection connection;
    private String id;
    @FXML
    private TextField loginText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;

    public void init(Pracownik pracownik, Connection connection2, String idB){
        id=idB;
        connection=connection2;
        loginText.setText(pracownik.getLogin());
        passwordText.setText(pracownik.getHaslo());
        nameText.setText(pracownik.getImie());
        surnameText.setText(pracownik.getNazwisko());
    }

    @FXML
    protected void changeUser(){

    }
    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("list.fxml"));
        root=loader.load();
        ListController listController = loader.getController();
        listController.init(id, connection);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
