package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class ListController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection connection;
    @FXML
    private ListView listView;
    private String id;

    public void init(String idB, Connection connection2){
        id=idB;
        listView.getItems().clear();
        connection=connection2;
        List<Pracownik> workersL = DatabaseConnection.getWorkers(connection);
            if(!workersL.isEmpty()){
                for(Pracownik pracownik : workersL){
                    if(!pracownik.isAdmin())
                        listView.getItems().add(pracownik.getId()+" "+pracownik.getImie()+" "+pracownik.getNazwisko());
                }
            }
        listView.setOnMouseClicked(event -> {
            if(event.getClickCount()==2)
            {
                try {
                    switch (id) {
                        case "editU":
                            switchToChanges(workersL.get(listView.getSelectionModel().getSelectedIndex()+1));
                            break;
                        case "deleteU":
                            if(DatabaseConnection.deleteUser(connection,workersL.get(listView.getSelectionModel().getSelectedIndex()+1).getId()))
                                init(id,connection);
                            break;
                    }
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    protected void switchToChanges(Pracownik pracownik) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeUser.fxml"));
        root=loader.load();
        ChangeUController changeUController = loader.getController();
        changeUController.init(pracownik,connection,id);
        stage = (Stage)listView.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
