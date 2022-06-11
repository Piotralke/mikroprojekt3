package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class AddOController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection connection;
    @FXML
    private ChoiceBox workers;


    public void init(Connection connection2){
        connection=connection2;
        List<Pracownik> workersL = DatabaseConnection.getWorkers(connection);
        if(!workersL.isEmpty()){
            for(Pracownik worker : workersL){
                if(!worker.isAdmin())
                    workers.getItems().add(worker.getId()+" "+worker.getImie()+" "+worker.getNazwisko());
            }
        }
    }

    @FXML
    protected void addOrder(){

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
