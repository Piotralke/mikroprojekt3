package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class UserController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection connection;
    private Pracownik worker;
    @FXML
    private Label helloLabel;
    @FXML
    private ListView listOrders;

    public void init(Connection connection2, Pracownik worker2){
        connection=connection2;
        worker=worker2;
        helloLabel.setText("Witaj: "+worker.getImie()+" "+worker.getNazwisko());
        List<Zlecenie> orders = DatabaseConnection.getOrders(connection, worker.getId());
        for(Zlecenie order : orders){
            listOrders.getItems().add(order.getIdZlecenia()+" "+order.getTytul()+" "+order.getOpis());
        }
        listOrders.setOnMouseClicked(event -> {
            if(event.getClickCount()==2)
            {
                try {
                    switchToProperties(orders.get(listOrders.getSelectionModel().getSelectedIndex()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    protected void switchToProperties(Zlecenie zlecenie) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("orderProperties.fxml"));
        root=loader.load();
        PropertiesController propertiesController = loader.getController();
        propertiesController.init(zlecenie,connection,worker);
        stage = (Stage)listOrders.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("panelLogowania.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
