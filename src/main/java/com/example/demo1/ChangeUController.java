package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Pracownik worker;
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

    public void init(Pracownik worker2, Connection connection2, String idB){
        id=idB;
        connection=connection2;
        worker=worker2;
        loginText.setText(worker.getLogin());
        passwordText.setText(worker.getHaslo());
        nameText.setText(worker.getImie());
        surnameText.setText(worker.getNazwisko());
        connection=connection2;
    }
    @FXML
    protected void changeUser(){
        if(DatabaseConnection.updateUser(connection, worker.getId(), passwordText.getText(), nameText.getText(), surnameText.getText())){
            doneLabel.setVisible(true);
            errorLabel.setVisible(false);
        }else{
            doneLabel.setVisible(false);
            errorLabel.setVisible(true);
        }
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
