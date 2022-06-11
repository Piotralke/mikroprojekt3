package com.example.demo1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML
    private TextField titleText;
    @FXML
    private TextArea descriptionText;
    @FXML
    private Label doneLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private Button button;
    private boolean descriptionCheck=false;
    private boolean textCheck=false;
    private boolean workerCheck=false;

    public void init(Connection connection2){
        check();
        connection=connection2;
        List<Pracownik> workersL = DatabaseConnection.getWorkers(connection);
        if(!workersL.isEmpty()){
            for(Pracownik worker : workersL){
                if(!worker.isAdmin())
                    workers.getItems().add(worker.getId()+" "+worker.getImie()+" "+worker.getNazwisko());
            }
        }

        descriptionText.setWrapText(true);
        descriptionText.textProperty().addListener((observable, oldValue, newValue) -> {
            descriptionCheck=!newValue.trim().isEmpty();
            check();
        });
        titleText.textProperty().addListener((observable, oldValue, newValue) -> {
            textCheck=!newValue.trim().isEmpty();
            check();
        });
        workers.getSelectionModel().selectedItemProperty().addListener((ov, value, new_value) -> {
                    workerCheck=!new_value.toString().trim().isEmpty();
                    check();
                });

    }

    @FXML
    protected void addOrder(){
        int spacja = workers.getSelectionModel().getSelectedItem().toString().indexOf(" ");
        int idWorker = Integer.valueOf(workers.getSelectionModel().getSelectedItem().toString().substring(0,spacja));
        if(DatabaseConnection.addOrder(connection,descriptionText.getText(), titleText.getText(),idWorker)){
            doneLabel.setVisible(true);
            errorLabel.setVisible(false);
        }else{
            doneLabel.setVisible(false);
            errorLabel.setVisible(true);
        }
    }

    private void check(){
        button.setDisable(!descriptionCheck || !textCheck || !workerCheck);
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
