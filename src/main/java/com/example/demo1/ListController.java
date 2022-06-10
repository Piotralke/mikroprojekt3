package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.Connection;

public class ListController {
    private Connection connection;
    @FXML
    private ListView listView;

    public void init(String id){
    listView.getItems().clear();


    }
}
