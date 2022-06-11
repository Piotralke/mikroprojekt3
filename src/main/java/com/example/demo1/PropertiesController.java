package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class PropertiesController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection connection;
    private Pracownik worker;
    private Zlecenie order;
    @FXML
    private Button startB;
    @FXML
    private Button endB;
    @FXML
    private TextArea descriptionText;
    @FXML
    private Label dateText;
    @FXML
    private Label titleText;
    @FXML
    private Label statusText;

    public void init(Zlecenie order2, Connection connection2, Pracownik worker2){
        worker=worker2;
        connection=connection2;
        order=order2;
        descriptionText.setWrapText(true);
        descriptionText.setEditable(false);
        titleText.setText("Tytuł:\n"+order.getTytul());
        statusText.setText("Status:\n"+order.getOpis());
        dateText.setText("Data utworzenia zlecenia:\n"+order.getData());
        descriptionText.setText(order.getTresc());
        if(order.getOpis().equals("Zlecone")){
            startB.setDisable(false);
            startB.setVisible(true);
        }else if(order.getOpis().equals("Przyjete")){
            endB.setDisable(false);
            endB.setVisible(true);
        }
    }

    @FXML
    protected void start(ActionEvent event) throws IOException {
        DatabaseConnection.updateOrder(connection, order.getIdZlecenia(), 2);
        goBack(event);
    }
    @FXML
    protected void end(ActionEvent event) throws IOException {
        DatabaseConnection.updateOrder(connection, order.getIdZlecenia(), 3);
        goBack(event);
    }
    
    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panelUser.fxml"));
        root=loader.load();
        UserController userController = loader.getController();
        userController.init(connection,worker);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
